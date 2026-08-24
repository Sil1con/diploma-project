package com.diploma.finance.investments.entity.investment_asset;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMMODITY")
public class Commodity extends InvestmentAsset {
    @Column(name = "symbol")
    private String symbol;

    protected Commodity() {
    }

    public Commodity(
            User user,
            String name,
            String symbol
    ) {
        super(user, name, InvestmentType.COMMODITY);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
