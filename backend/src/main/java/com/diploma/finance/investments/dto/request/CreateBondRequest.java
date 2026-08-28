package com.diploma.finance.investments.dto.request;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateBondRequest extends CreateInvestmentRequest {
    @NotBlank
    private String issuer;

    @NotBlank
    private String isin;

    @NotNull
    @Positive
    private BigDecimal faceValue;

    @NotNull
    @PositiveOrZero
    private BigDecimal couponRate;

    @NotNull
    private LocalDate maturityDate;

    protected CreateBondRequest(
            @JsonProperty("userId") Long userId,
            @JsonProperty("name") String name,
            @JsonProperty("type") InvestmentType type,
            @JsonProperty("transactionType") TransactionType transactionType,
            @JsonProperty("quantity") BigDecimal quantity,
            @JsonProperty("pricePerUnit") BigDecimal pricePerUnit,
            @JsonProperty("purchaseDate") LocalDate purchaseDate,
            @JsonProperty("notes") String notes,
            @JsonProperty("issuer") String issuer,
            @JsonProperty("isin") String isin,
            @JsonProperty("faceValue") BigDecimal faceValue,
            @JsonProperty("couponRate") BigDecimal couponRate,
            @JsonProperty("maturityDate") LocalDate maturityDate
    ) {
        super(userId, name, type, transactionType, quantity, pricePerUnit, purchaseDate, notes);
        this.issuer = issuer;
        this.isin = isin;
        this.faceValue = faceValue;
        this.couponRate = couponRate;
        this.maturityDate = maturityDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getIsin() {
        return isin;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public BigDecimal getCouponRate() {
        return couponRate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }
}
