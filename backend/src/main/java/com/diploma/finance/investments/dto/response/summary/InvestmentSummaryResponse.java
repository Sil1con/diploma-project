package com.diploma.finance.investments.dto.response.summary;

import com.diploma.finance.investments.entity.enums.InvestmentType;

import java.math.BigDecimal;

public class InvestmentSummaryResponse {
    private final Long investmentId;
    private final String investmentName;
    private final InvestmentType investmentType;
    private final BigDecimal totalValue;

    public InvestmentSummaryResponse(
            Long investmentId,
            String investmentName,
            InvestmentType investmentType,
            BigDecimal totalValue
    ) {
        this.investmentId = investmentId;
        this.investmentName = investmentName;
        this.investmentType = investmentType;
        this.totalValue = totalValue;
    }

    public Long getInvestmentId() {
        return investmentId;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }
}
