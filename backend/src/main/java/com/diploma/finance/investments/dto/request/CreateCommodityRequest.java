package com.diploma.finance.investments.dto.request;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateCommodityRequest extends CreateInvestmentRequest {
    @NotBlank
    private String symbol;
    protected CreateCommodityRequest(
            @JsonProperty("userId") Long userId,
            @JsonProperty("name") String name,
            @JsonProperty("type") InvestmentType type,
            @JsonProperty("quantity") BigDecimal quantity,
            @JsonProperty("pricePerUnit") BigDecimal pricePerUnit,
            @JsonProperty("purchaseDate") LocalDate purchaseDate,
            @JsonProperty("notes") String notes,
            @JsonProperty("symbol") String symbol
    ) {
        super(userId, name, type, quantity, pricePerUnit, purchaseDate, notes);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
