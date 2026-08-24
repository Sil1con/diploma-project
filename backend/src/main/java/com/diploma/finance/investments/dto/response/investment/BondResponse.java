package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BondResponse extends InvestmentResponse {
    private String issuer;
    private String isin;

    private BigDecimal faceValue;

    private BigDecimal couponRate;

    private LocalDate maturityDate;

    public BondResponse(
            Long id,
            Long userId,
            String investmentName,
            String issuer,
            String isin,
            BigDecimal faceValue,
            BigDecimal couponRate,
            LocalDate maturityDate
    ) {
        super(id, userId, investmentName, InvestmentType.COMMODITY);
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