package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.Currency;
import com.diploma.finance.investments.entity.enums.InvestmentType;

public class CashResponse extends InvestmentResponse{
    private Currency currency;
    public CashResponse(
            Long id,
            String investmentName,
            Currency currency
    ) {
        super(id, investmentName, InvestmentType.CASH);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }
}
