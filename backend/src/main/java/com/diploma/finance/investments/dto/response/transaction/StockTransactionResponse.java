package com.diploma.finance.investments.dto.response.transaction;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockTransactionResponse extends TransactionResponse {
    private String ticker;
    public StockTransactionResponse(
            Long assetId,
            Long transactionId,
            String investmentName,
            TransactionType transactionType,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate transactionDate,
            String notes,
            String ticker
    ) {
        super(
                assetId,
                transactionId,
                investmentName,
                InvestmentType.STOCK,
                transactionType, quantity,
                pricePerUnit,
                transactionDate,
                notes
        );
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }
}
