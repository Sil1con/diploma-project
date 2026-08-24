package com.diploma.finance.investments.dto.request;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateEtfRequest extends CreateInvestmentRequest {
    @NotBlank
    private String ticker;

    @NotBlank
    private String brokerAccount;

    protected CreateEtfRequest(
            @JsonProperty("userId") Long userId,
            @JsonProperty("name") String name,
            @JsonProperty("type") InvestmentType type,
            @JsonProperty("quantity") BigDecimal quantity,
            @JsonProperty("pricePerUnit") BigDecimal pricePerUnit,
            @JsonProperty("purchaseDate") LocalDate purchaseDate,
            @JsonProperty("notes") String notes,
            @JsonProperty("ticker") String ticker,
            @JsonProperty("brokerAccount") String brokerAccount
    ) {
        super(userId, name, type, quantity, pricePerUnit, purchaseDate, notes);
        this.ticker = ticker;
        this.brokerAccount = brokerAccount;
    }

    public String getTicker() {
        return ticker;
    }

    public String getBrokerAccount() {
        return brokerAccount;
    }
}
