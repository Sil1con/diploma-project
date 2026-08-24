package com.diploma.finance.investments.entity.investment_asset;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.user.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STOCK")
public class Stock extends InvestmentAsset {
    @Column(name = "ticker")
    private String ticker;

    protected Stock() {
    }

    public Stock(
            User user,
            String name,
            String ticker
    ) {
        super(user, name, InvestmentType.STOCK);
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    @Override
    public InvestmentType getType() {
        return InvestmentType.STOCK;
    }
}