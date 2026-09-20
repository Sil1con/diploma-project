package com.diploma.finance.expenditures.repository;

import com.diploma.finance.expenditures.entity.Expenditure;
import com.diploma.finance.expenditures.entity.enums.ExpenditureCategory;
import com.diploma.finance.expenditures.entity.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {
    List<Expenditure> findAllByUserId(Long userId);

    List<Expenditure> findByUserIdAndCategoryAndExpenditureDateGreaterThanEqualAndExpenditureDateLessThanEqual(
            Long userId,
            ExpenditureCategory category,
            LocalDate startDate,
            LocalDate endDate
    );

    List<Expenditure> findAllByUserIdAndExpenditureDateGreaterThanEqualAndExpenditureDateLessThanEqual(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );

    Optional<Expenditure> findByUserIdAndId(Long userId, Long expenditureId);

    Optional<Expenditure> findByUserIdAndNameAndCategoryAndExpenditureDateGreaterThanEqualAndExpenditureDateLessThanEqual(
            Long userId,
            String name,
            ExpenditureCategory category,
            LocalDate startDate,
            LocalDate endDate
    );

    boolean existsByUserIdAndNameAndCategoryAndAmountAndExpenditureDateAndPaymentMethodAndVendor(
            Long userId,
            String name,
            ExpenditureCategory category,
            BigDecimal amount,
            LocalDate expenditureDate,
            PaymentMethod paymentMethod,
            String vendor
    );

    @Query("""
        SELECT COALESCE(SUM(e.amount), 0)
        FROM Expenditure e
        WHERE e.user.id = :userId
            AND e.expenditureDate >= :startDate
            AND e.expenditureDate <= :endDate
        """)
    BigDecimal calculateTotalSpent(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
        SELECT COALESCE(SUM(e.amount), 0)
        FROM Expenditure e
        WHERE e.user.id = :userId
            AND e.category = :category
            AND e.expenditureDate >= :startDate
            AND e.expenditureDate <= :endDate
        """)
    BigDecimal calculateTotalSpentByCategoryForPeriod(
            @Param("userId") Long userId,
            @Param("category") ExpenditureCategory category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
        SELECT COALESCE(SUM(e.amount), 0)
        FROM Expenditure e
        WHERE e.user.id = :userId
            AND e.paymentMethod = :paymentMethod
            AND e.expenditureDate >= :startDate
            AND e.expenditureDate <= :endDate
        """)
    BigDecimal calculateTotalSpentByPaymentMethodForPeriod(
            @Param("userId") Long userId,
            @Param("paymentMethod") PaymentMethod paymentMethod,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
        SELECT COALESCE(SUM(e.amount), 0)
        FROM Expenditure e
        WHERE e.user.id = :userId
            AND e.vendor = :vendor
            AND e.expenditureDate >= :startDate
            AND e.expenditureDate <= :endDate
        """)
    BigDecimal calculateTotalSpentByVendorForPeriod(
            @Param("userId") Long userId,
            @Param("vendor") String vendor,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
