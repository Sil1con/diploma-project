package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;

public class CryptoResponse extends InvestmentResponse {
    private String symbol;
    public CryptoResponse(
            Long id,
            String investmentName,
            String symbol
    ) {
        super(id, investmentName, InvestmentType.CRYPTO);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}