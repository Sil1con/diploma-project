package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;

public class CommodityResponse extends InvestmentResponse {
    private String symbol;
    public CommodityResponse(
            Long id,
            String investmentName,
            String symbol
    ) {
        super(id, investmentName, InvestmentType.COMMODITY);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}