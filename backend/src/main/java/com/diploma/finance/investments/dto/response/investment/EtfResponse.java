package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;

public class EtfResponse extends InvestmentResponse {
    private String ticker;
    public EtfResponse(
            Long id,
            Long userId,
            String investmentName,
            String ticker
    ) {
        super(id, userId, investmentName, InvestmentType.ETF);
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }
}
