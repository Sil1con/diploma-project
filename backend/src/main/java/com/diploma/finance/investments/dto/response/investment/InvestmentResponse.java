package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class InvestmentResponse {
    private final Long assetId;
    private final String investmentName;
    private final InvestmentType type;

    protected InvestmentResponse(
            Long assetId,
            String investmentName,
            InvestmentType type
    ) {
        this.assetId = assetId;
        this.investmentName = investmentName;
        this.type = type;
    }

    public Long getAssetId() {
        return assetId;
    }


    public String getInvestmentName() {
        return investmentName;
    }

    public InvestmentType getType() {
        return type;
    }

}