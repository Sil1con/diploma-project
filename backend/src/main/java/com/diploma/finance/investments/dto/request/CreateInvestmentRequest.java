package com.diploma.finance.investments.dto.request;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateStockRequest.class, name = "STOCK"),
        @JsonSubTypes.Type(value = CreateBondRequest.class, name = "BOND"),
        @JsonSubTypes.Type(value = CreateEtfRequest.class, name = "ETF"),
        @JsonSubTypes.Type(value = CreateCryptoRequest.class, name = "CRYPTO"),
        @JsonSubTypes.Type(value = CreateCommodityRequest.class, name = "COMMODITY"),
        @JsonSubTypes.Type(value = CreateCashRequest.class, name = "CASH")
})
public abstract class CreateInvestmentRequest {
    @NotNull
    private Long userId;

    @NotBlank
    private String name;

    @NotNull
    private InvestmentType type;

    @NotNull
    @Positive
    private BigDecimal quantity;

    @NotNull
    @Positive
    private BigDecimal pricePerUnit;

    @NotNull
    private LocalDate purchaseDate;

    private String notes;

    protected CreateInvestmentRequest(
            Long userId,
            String name,
            InvestmentType type,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate purchaseDate,
            String notes
    ) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.purchaseDate = purchaseDate;
        this.notes = notes;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public InvestmentType getType() {
        return type;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String getNotes() {
        return notes;
    }
}
