package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;

public class StockResponse extends InvestmentResponse {
    private String ticker;
    public StockResponse(
            Long id,
            String investmentName,
            String ticker
    ) {
        super(id, investmentName, InvestmentType.STOCK);
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }
}
