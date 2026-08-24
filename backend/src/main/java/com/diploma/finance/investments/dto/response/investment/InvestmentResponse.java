package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class InvestmentResponse {
    private final Long id;
    private final Long userId;
    private final String investmentName;
    private final InvestmentType type;

    protected InvestmentResponse(
            Long id,
            Long userId,
            String investmentName,
            InvestmentType type
    ) {
        this.id = id;
        this.userId = userId;
        this.investmentName = investmentName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public InvestmentType getType() {
        return type;
    }

}