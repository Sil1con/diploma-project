package com.diploma.finance.income.dto.response;

import com.diploma.finance.income.entity.enums.IncomeCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeResponse {
    private Long incomeId;

    private String name;

    private BigDecimal amount;

    private IncomeCategory category;

    private LocalDate incomeDate;

    private String description;

    public IncomeResponse(
            Long incomeId,
            String name,
            BigDecimal amount,
            IncomeCategory category,
            LocalDate incomeDate,
            String description
    ) {
        this.incomeId = incomeId;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.incomeDate = incomeDate;
        this.description = description;
    }

    public Long getId() {
        return incomeId;
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

