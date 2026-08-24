package com.diploma.finance.investments.entity.investment_asset;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ETF")
public class ETF extends InvestmentAsset {
    @Column(name = "ticker")
    private String ticker;

    protected ETF() {
    }

    public ETF(
            User user,
            String name,
            String ticker
    ) {
        super(user, name, InvestmentType.ETF);
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    @Override
    public InvestmentType getType() {
        return InvestmentType.ETF;
    }
}
