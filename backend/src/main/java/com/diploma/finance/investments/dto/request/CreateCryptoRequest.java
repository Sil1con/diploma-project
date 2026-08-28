package com.diploma.finance.investments.dto.request;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateCryptoRequest extends CreateInvestmentRequest {
    @NotBlank
    private String symbol;

    @NotBlank
    private String wallet;
    protected CreateCryptoRequest(
            @JsonProperty("userId") Long userId,
            @JsonProperty("name") String name,
            @JsonProperty("type") InvestmentType type,
            @JsonProperty("transactionType") TransactionType transactionType,
            @JsonProperty("quantity") BigDecimal quantity,
            @JsonProperty("pricePerUnit") BigDecimal pricePerUnit,
            @JsonProperty("purchaseDate") LocalDate purchaseDate,
            @JsonProperty("notes") String notes,
            @JsonProperty("symbol") String symbol,
            @JsonProperty("wallet") String wallet
    ) {
        super(userId, name, type, transactionType, quantity, pricePerUnit, purchaseDate, notes);
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
