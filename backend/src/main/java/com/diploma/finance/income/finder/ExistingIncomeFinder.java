package com.diploma.finance.income.finder;

import com.diploma.finance.exception.entity.request.InvalidRequestException;
import com.diploma.finance.income.entity.IncomeSource;
import com.diploma.finance.income.entity.enums.IncomeCategory;
import com.diploma.finance.income.entity.records.MonthBoundaries;
import com.diploma.finance.income.repository.IncomeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExistingIncomeFinder {
    private final IncomeRepository incomeRepository;

    public ExistingIncomeFinder(
            IncomeRepository incomeRepository
    ) {
        this.incomeRepository = incomeRepository;
    }

    private static void validateIncomeSourcesQuantity(IncomeSource incomeSource, List<IncomeSource> existingSources) {
        if (incomeSource.getCategory().equals(IncomeCategory.SALARY)) {
            if (existingSources.size() >= 2) {
                throw new InvalidRequestException(
                        "You cannot have more than 2 income sources of category " + IncomeCategory.SALARY
                );
            }
        }

        if (incomeSource.getCategory().equals(IncomeCategory.FREELANCE)) {
            if (existingSources.size() >= 3) {
                throw new InvalidRequestException(
                        "You cannot have more than 3 income sources of category " + IncomeCategory.FREELANCE
                );
            }
        }

        if (incomeSource.getCategory().equals(IncomeCategory.SCHOLARSHIP)) {
            if (existingSources.size() >= 2) {
                throw new InvalidRequestException(
                        "You cannot have more than 2 income sources of category " + IncomeCategory.SCHOLARSHIP
                );
            }
        }

        if (incomeSource.getCategory().equals(IncomeCategory.OTHER)) {
            if (existingSources.size() >= 2) {
                throw new InvalidRequestException(
                        "You cannot have more than 2 income sources of category " + IncomeCategory.OTHER
                );
            }
        }
    }

    public Optional<IncomeSource> find(IncomeSource incomeSource, MonthBoundaries monthBoundaries) {
        List<IncomeSource> existingSources = incomeRepository.findByUserIdAndCategoryAndIncomeDateGreaterThanEqualAndIncomeDateLessThan(
                incomeSource.getUser().getId(),
                incomeSource.getCategory(),
                monthBoundaries.startOfMonth(),
                monthBoundaries.startOfNextMonth()
        );

        validateIncomeSourcesQuantity(incomeSource, existingSources);

        return incomeRepository.findByUserIdAndNameAndCategoryAndIncomeDateGreaterThanEqualAndIncomeDateLessThan(
                incomeSource.getUser().getId(),
                incomeSource.getName(),
                incomeSource.getCategory(),
                monthBoundaries.startOfMonth(),
                monthBoundaries.startOfNextMonth()
        );
    }
}
