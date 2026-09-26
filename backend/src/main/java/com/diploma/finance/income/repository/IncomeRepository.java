package com.diploma.finance.income.repository;

import com.diploma.finance.income.entity.IncomeSource;
import com.diploma.finance.income.entity.enums.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<IncomeSource, Long> {
    List<IncomeSource> findAllByUserId(Long userId);

    Optional<IncomeSource> findByUserIdAndId(Long userId, Long incomeId);

    Optional<IncomeSource> findByUserIdAndNameAndAmountAndCategory(Long userId, String name, BigDecimal amount, IncomeCategory category);

    List<IncomeSource> findByUserIdAndCategoryAndIncomeDateGreaterThanEqualAndIncomeDateLessThan(
        Long userId,
        IncomeCategory category,
        LocalDate startDate,
        LocalDate endDate
    );

    List<IncomeSource> findAllByUserIdAndIncomeDateGreaterThanEqualAndIncomeDateLessThan(
        Long userId,
        LocalDate startDate,
        LocalDate endDate
    );

    Optional<IncomeSource> findByUserIdAndNameAndCategoryAndIncomeDateGreaterThanEqualAndIncomeDateLessThan(
            Long userId,
            String name,
            IncomeCategory category,
            LocalDate startDate,
            LocalDate endDate
    );
}
