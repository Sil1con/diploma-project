package com.diploma.finance.investments.dto.request;

import com.diploma.finance.investments.entity.enums.Currency;
import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateCashRequest extends CreateInvestmentRequest {
    private final Currency currency;
    protected CreateCashRequest(
            @JsonProperty("userId") Long userId,
            @JsonProperty("name") String name,
            @JsonProperty("type") InvestmentType type,
            @JsonProperty("transactionType") TransactionType transactionType,
            @JsonProperty("quantity") BigDecimal quantity,
            @JsonProperty("pricePerUnit") BigDecimal pricePerUnit,
            @JsonProperty("purchaseDate") LocalDate purchaseDate,
            @JsonProperty("notes") String notes,
            @JsonProperty("currency") Currency currency
    ) {
        super(userId, name, type, transactionType, quantity, pricePerUnit, purchaseDate, notes);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }
}
