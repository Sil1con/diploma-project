package com.diploma.finance.investments.dto.response.transaction;

import com.diploma.finance.investments.entity.enums.Currency;
import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CashTransactionResponse extends TransactionResponse {
    private Currency currency;
    public CashTransactionResponse(
            Long assetId,
            Long transactionId,
            String investmentName,
            TransactionType transactionType,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate transactionDate,
            String notes,
            Currency currency
    ) {
        super(
                assetId,
                transactionId,
                investmentName,
                InvestmentType.CASH,
                transactionType, quantity,
                pricePerUnit,
                transactionDate,
                notes
        );
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }
}
