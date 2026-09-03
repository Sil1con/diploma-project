package com.diploma.finance.investments.dto.response.transaction;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CryptoTransactionResponse extends TransactionResponse {
    private String symbol;
    private String wallet;
    public CryptoTransactionResponse(
            Long assetId,
            Long transactionId,
            String investmentName,
            TransactionType transactionType,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate transactionDate,
            String notes,
            String symbol,
            String wallet
    ) {
        super(
                assetId,
                transactionId,
                investmentName,
                InvestmentType.CRYPTO,
                transactionType, quantity,
                pricePerUnit,
                transactionDate,
                notes
        );
        this.symbol = symbol;
        this.wallet = wallet;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getWallet() {
        return wallet;
    }
}