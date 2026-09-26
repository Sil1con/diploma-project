package com.diploma.finance.income.service;

import com.diploma.finance.income.entity.records.MonthBoundaries;
import com.diploma.finance.utilities.exception.entity.request.InvalidRequestException;
import com.diploma.finance.income.dto.request.CreateIncomeRequest;
import com.diploma.finance.income.dto.response.IncomeResponse;
import com.diploma.finance.income.entity.IncomeSource;
import com.diploma.finance.income.finder.ExistingIncomeFinder;
import com.diploma.finance.income.mapper.IncomeRequestMapper;
import com.diploma.finance.income.mapper.IncomeResponseMapper;
import com.diploma.finance.income.repository.IncomeRepository;
import com.diploma.finance.income.validator.CreateIncomeRequestValidator;
import com.diploma.finance.user.entity.User;
import com.diploma.finance.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    private final UserRepository userRepository;
    private final IncomeRepository incomeRepository;
    private final ExistingIncomeFinder incomeFinder;

    public IncomeService(
            UserRepository userRepository,
            IncomeRepository incomeRepository,
            ExistingIncomeFinder incomeFinder
    ) {
        this.userRepository = userRepository;
        this.incomeRepository = incomeRepository;
        this.incomeFinder = incomeFinder;
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new InvalidRequestException("User not found")
                );
    }

    private MonthBoundaries getMonthBoundaries(YearMonth month) {
        return new MonthBoundaries(
                month.atDay(1),
                month.plusMonths(1).atDay(1));
    }

    private IncomeSource createIncomeFromRequest(CreateIncomeRequest request) {
        User user = getUser(request.getUserId());

        return IncomeRequestMapper.toIncome(request, user);
    }

    private IncomeSource resolveIncomeSource(IncomeSource incomingSource) {
        YearMonth month = YearMonth.from(incomingSource.getIncomeDate());

        MonthBoundaries monthBoundaries = getMonthBoundaries(month);

        Optional<IncomeSource> existingSource = incomeFinder.find(incomingSource, monthBoundaries);

        if (existingSource.isPresent()) {
            throw new InvalidRequestException(
                    "Income source that you intend to create already exists"
            );
        }

        return incomeRepository.save(incomingSource);
    }

    private void sortIncomeSourcesDesc(List<IncomeSource> incomeSources) {
        for (int i = 0; i < incomeSources.size(); i++) {
            for (int j = 0; j < incomeSources.size(); j++) {
                if (incomeSources.get(i).getAmount()
                        .compareTo(incomeSources.get(j).getAmount()) > 0) {
                    IncomeSource temp = incomeSources.get(i);

                    incomeSources.set(i, incomeSources.get(j));
                    incomeSources.set(j, temp);
                }
            }
        }
    }

    public IncomeResponse createIncomeSource(CreateIncomeRequest incomeRequest) {
        CreateIncomeRequestValidator.validate(incomeRequest);

        IncomeSource incomingSource = createIncomeFromRequest(incomeRequest);

        IncomeSource incomeSource = resolveIncomeSource(incomingSource);

        return IncomeResponseMapper.toResponse(incomeSource);
    }

    public List<IncomeResponse> getCurrentMonthIncomeSources(Long userId) {
        YearMonth currentMonth = YearMonth.now();

        return getIncomeSourcesForMonth(userId, currentMonth);
    }

    public List<IncomeResponse> getIncomeSourcesForMonth(Long userId, YearMonth month) {
        MonthBoundaries monthBoundaries = getMonthBoundaries(month);

        User user = getUser(userId);

        List<IncomeSource> incomeSources = incomeRepository.findAllByUserIdAndIncomeDateGreaterThanEqualAndIncomeDateLessThan(
                user.getId(),
                monthBoundaries.startOfMonth(),
                monthBoundaries.startOfNextMonth()
        );

        sortIncomeSourcesDesc(incomeSources);

        return IncomeResponseMapper.toResponses(incomeSources);
    }

    public BigDecimal calculateCurrentIncomeTotalValue(Long userId) {
        YearMonth currentMonth = YearMonth.now();

        return calculateIncomeTotalValueForMonth(userId, currentMonth);
    }

    public BigDecimal calculateIncomeTotalValueForMonth(Long userId, YearMonth month) {
        User user = getUser(userId);

        MonthBoundaries monthBoundaries = getMonthBoundaries(month);

        BigDecimal totalValue = BigDecimal.ZERO;

        List<IncomeSource> incomeSources = incomeRepository.findAllByUserIdAndIncomeDateGreaterThanEqualAndIncomeDateLessThan(
                user.getId(),
                monthBoundaries.startOfMonth(),
                monthBoundaries.startOfNextMonth()
        );

        for (IncomeSource source : incomeSources) {
            totalValue = totalValue.add(source.getAmount());
        }

        return totalValue;
    }

    public void deleteIncome(Long userId, Long incomeId) {
        User user = getUser(userId);

        Optional<IncomeSource> existingSource = incomeFinder.findByIncomeId(user.getId(), incomeId);

        if (existingSource.isEmpty()) {
            throw new InvalidRequestException(
                    "Income source not found"
            );
        }

        incomeRepository.delete(existingSource.get());
    }
}
