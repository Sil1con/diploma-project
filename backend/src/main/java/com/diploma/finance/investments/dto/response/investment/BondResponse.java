package com.diploma.finance.investments.dto.response.investment;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BondResponse extends InvestmentResponse {
    private String issuer;
    private String isin;
    private BigDecimal faceValue;
    private BigDecimal couponRate;
    private LocalDate maturityDate;

    public BondResponse(
            Long assetId,
            String investmentName,
            String issuer,
            String isin,
            BigDecimal faceValue,
            BigDecimal couponRate,
            LocalDate maturityDate
    ) {
        super(assetId, investmentName, InvestmentType.BOND);
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