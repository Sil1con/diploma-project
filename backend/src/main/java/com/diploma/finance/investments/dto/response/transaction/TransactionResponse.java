package com.diploma.finance.investments.dto.response.transaction;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class TransactionResponse {
    private final Long assetId;
    private final Long transactionId;
    private final String investmentName;
    private final InvestmentType type;
    private final TransactionType transactionType;
    private final BigDecimal quantity;
    private final BigDecimal pricePerUnit;
    private final LocalDate transactionDate;
    private final String notes;

    protected TransactionResponse(
            Long assetId,
            Long transactionId,
            String investmentName,
            InvestmentType type,
            TransactionType transactionType,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate transactionDate,
            String notes
    ) {
        this.assetId = assetId;
        this.transactionId = transactionId;
        this.investmentName = investmentName;
        this.type = type;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.transactionDate = transactionDate;
        this.notes = notes;
    }

    public Long getAssetId() {
        return assetId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public InvestmentType getType() {
        return type;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getNotes() {
        return notes;
    }
}