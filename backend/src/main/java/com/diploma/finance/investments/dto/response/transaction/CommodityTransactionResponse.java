package com.diploma.finance.investments.dto.response.transaction;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CommodityTransactionResponse extends TransactionResponse {
    private final String symbol;
    public CommodityTransactionResponse(
            Long assetId,
            Long transactionId,
            String investmentName,
            TransactionType transactionType,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate transactionDate,
            String notes,
            String symbol
    ) {
        super(
                assetId,
                transactionId,
                investmentName,
                InvestmentType.COMMODITY,
                transactionType, quantity,
                pricePerUnit,
                transactionDate,
                notes
        );
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}