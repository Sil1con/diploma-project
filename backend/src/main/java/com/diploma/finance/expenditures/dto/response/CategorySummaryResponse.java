package com.diploma.finance.expenditures.dto.response;

import com.diploma.finance.expenditures.entity.enums.ExpenditureCategory;

import java.math.BigDecimal;

public class CategorySummaryResponse {
    private ExpenditureCategory category;
    private BigDecimal totalAmount;

    public CategorySummaryResponse(
            ExpenditureCategory category,
            BigDecimal totalAmount
    ) {
        this.category = category;
        this.totalAmount = totalAmount;
    }

    public ExpenditureCategory getCategory() {
        return category;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
