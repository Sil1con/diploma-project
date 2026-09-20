package com.diploma.finance.income.dto.request;

import com.diploma.finance.income.entity.enums.IncomeCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateIncomeRequest {
    @NotNull
    private Long userId;

    @NotNull
    private String name;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private IncomeCategory category;

    @NotNull
    private LocalDate incomeDate;

    private String description;

    public CreateIncomeRequest(
        Long userId,
        String name,
        BigDecimal amount,
        IncomeCategory category,
        LocalDate incomeDate,
        String description
    ) {
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.incomeDate = incomeDate;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public IncomeCategory getCategory() {
        return category;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public String getDescription() {
        return description;
    }
}
