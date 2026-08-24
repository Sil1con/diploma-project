package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;

public class CommodityResponse extends InvestmentResponse {
    private final String symbol;
    public CommodityResponse(
            Long id,
            Long userId,
            String investmentName,
            String symbol
    ) {
        super(id, userId, investmentName, InvestmentType.COMMODITY);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}