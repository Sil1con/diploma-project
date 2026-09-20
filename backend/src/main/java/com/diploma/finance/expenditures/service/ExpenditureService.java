package com.diploma.finance.expenditures.service;

import com.diploma.finance.expenditures.validator.DatesValidator;
import com.diploma.finance.utilities.exception.entity.request.InvalidRequestException;
import com.diploma.finance.expenditures.dto.request.CreateExpenditureRequest;
import com.diploma.finance.expenditures.dto.response.CategorySummaryResponse;
import com.diploma.finance.expenditures.dto.response.ExpenditureResponse;
import com.diploma.finance.expenditures.entity.Expenditure;
import com.diploma.finance.expenditures.entity.enums.ExpenditureCategory;
import com.diploma.finance.expenditures.finder.ExistingExpenditureFinder;
import com.diploma.finance.expenditures.mapper.ExpenditureRequestMapper;
import com.diploma.finance.expenditures.mapper.ExpenditureResponseMapper;
import com.diploma.finance.expenditures.repository.ExpenditureRepository;
import com.diploma.finance.expenditures.validator.CreateExpenditureRequestValidator;
import com.diploma.finance.user.entity.User;
import com.diploma.finance.user.repository.UserRepository;
import com.diploma.finance.utilities.record.PeriodBoundaries;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenditureService {
    private final UserRepository userRepository;
    private final ExpenditureRepository expenditureRepository;
    private final ReceiptPathService receiptPathService;
    private final ExistingExpenditureFinder expenditureFinder;

    public ExpenditureService(
            UserRepository userRepository,
            ExpenditureRepository expenditureRepository,
            ExistingExpenditureFinder expenditureFinder,
            ReceiptPathService receiptPathService
    ) {
        this.userRepository = userRepository;
        this.expenditureRepository = expenditureRepository;
        this.receiptPathService = receiptPathService;
        this.expenditureFinder = expenditureFinder;
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new InvalidRequestException("User not found")
                );
    }

    private PeriodBoundaries getMonthBoundaries(YearMonth month) {
        return new PeriodBoundaries(
                month.atDay(1),
                month.atEndOfMonth()
        );
    }

    private PeriodBoundaries getPeriodBoundaries(LocalDate startDate, LocalDate endDate) {
        return new PeriodBoundaries(
                startDate,
                endDate
        );
    }

    private Expenditure createExpenditureFromRequest(CreateExpenditureRequest request, MultipartFile receipt) {
        User user = getUser(request.getUserId());

        //I should add here ReceiptPathService to save the receipt image and return the path to the image

        return ExpenditureRequestMapper.toExpenditure(request, user);
    }

    private Expenditure resolveExpenditure(Expenditure incomingExpenditure) {
        boolean isFound = expenditureFinder.find(incomingExpenditure);

        if (isFound) throw new InvalidRequestException("This expenditure already exists");

        return expenditureRepository.save(incomingExpenditure);
    }

    private void sortCategoriesSummaries(List<CategorySummaryResponse> responseList) {
        for (int i = 0; i < responseList.size(); i++) {
            for (int j = 0; j < responseList.size(); j++) {

                if (responseList.get(i).getTotalAmount()
                        .compareTo(
                                responseList.get(j).getTotalAmount()) > 0) {

                    CategorySummaryResponse temp = responseList.get(i);
                    responseList.set(i, responseList.get(j));
                    responseList.set(j, temp);
                }
            }
        }
    }

    public ExpenditureResponse createExpenditure(CreateExpenditureRequest expenditureRequest, MultipartFile receipt) {
        CreateExpenditureRequestValidator.validate(expenditureRequest);
        CreateExpenditureRequestValidator.validateReceipt(receipt);

        Expenditure incomingExpenditure = createExpenditureFromRequest(expenditureRequest, receipt);

        Expenditure expenditure = resolveExpenditure(incomingExpenditure);

        return ExpenditureResponseMapper.toResponse(expenditure);
    }

    public ExpenditureResponse getExpenditure(Long userId, Long expenditureId) {
        User user = getUser(userId);

        Expenditure foundExpenditure = expenditureRepository.
                findByUserIdAndId(user.getId(), expenditureId)
                    .orElseThrow(() ->
                            new InvalidRequestException("Expenditure does not exist")
                    );

        return ExpenditureResponseMapper.toResponse(foundExpenditure);
    }

    public List<ExpenditureResponse> getCurrentMonthExpenditures(Long userId) {
        User user = getUser(userId);

        YearMonth currentMonth = YearMonth.now();

        PeriodBoundaries monthBoundaries = getMonthBoundaries(currentMonth);

        return getExpendituresForPeriod(
                user.getId(),
                monthBoundaries.startOfPeriod(),
                monthBoundaries.endOfPeriod()
        );
    }

    public List<ExpenditureResponse> getExpendituresForPeriod(Long userId, LocalDate startDate, LocalDate endDate) {
        DatesValidator.validate(startDate, endDate);

        User user = getUser(userId);

        PeriodBoundaries periodBoundaries = getPeriodBoundaries(startDate, endDate);

        List<Expenditure> expenditures = expenditureRepository.findAllByUserIdAndExpenditureDateGreaterThanEqualAndExpenditureDateLessThanEqual(
                user.getId(),
                periodBoundaries.startOfPeriod(),
                periodBoundaries.endOfPeriod()
        );

        return ExpenditureResponseMapper.toResponses(expenditures);
    }

    public List<CategorySummaryResponse> getCurrentMonthCategoriesSummary(Long userId) {
        User user = getUser(userId);

        YearMonth currentMonth = YearMonth.now();

        PeriodBoundaries monthBoundaries = getMonthBoundaries(currentMonth);

        return getCategoriesSummaryForPeriod(
                user.getId(),
                monthBoundaries.startOfPeriod(),
                monthBoundaries.endOfPeriod()
        );
    }

    public List<CategorySummaryResponse> getCategoriesSummaryForPeriod(Long userId, LocalDate startDate, LocalDate endDate) {
        User user = getUser(userId);

        PeriodBoundaries periodBoundaries = getPeriodBoundaries(startDate, endDate);

        List<CategorySummaryResponse> summaryResponses = new ArrayList<>();

        for (ExpenditureCategory category : ExpenditureCategory.values()) {
            CategorySummaryResponse summary;

            BigDecimal totalSpentByCategory = expenditureRepository
                    .calculateTotalSpentByCategoryForPeriod(
                            user.getId(),
                            category,
                            periodBoundaries.startOfPeriod(),
                            periodBoundaries.endOfPeriod()
                    );

            summary = new CategorySummaryResponse(category, totalSpentByCategory);

            summaryResponses.add(summary);
        }

        sortCategoriesSummaries(summaryResponses);

        return summaryResponses;
    }
}