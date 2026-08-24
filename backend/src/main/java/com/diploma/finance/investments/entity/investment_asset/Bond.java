package com.diploma.finance.investments.entity.investment_asset;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("BOND")
public class Bond extends InvestmentAsset {
    @Column(name = "issuer")
    private String issuer;

    @Column(name = "isin")
    private String isin;

    @Column(name = "face_value")
    private BigDecimal faceValue;

    @Column(name = "coupon_rate")
    private BigDecimal couponRate;

    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    protected Bond() {
    }

    public Bond(
            User user,
            String investmentName,
            String issuer,
            String isin,
            BigDecimal faceValue,
            BigDecimal couponRate,
            LocalDate maturityDate
    ) {
        super(user, investmentName, InvestmentType.BOND);
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

//    public void setIssuer(String issuer) {
//        this.issuer = issuer;
//    }
//
//    public void setIsin(String isin) {
//        this.isin = isin;
//    }
//
//    public void setFaceValue(BigDecimal faceValue) {
//        this.faceValue = faceValue;
//    }
//
//    public void setCouponRate(BigDecimal couponRate) {
//        this.couponRate = couponRate;
//    }
//
//    public void setMaturityDate(LocalDate maturityDate) {
//        this.maturityDate = maturityDate;
//    }
}
