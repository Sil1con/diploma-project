package com.diploma.finance.investments.entity.investment_asset;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CRYPTO")
public class Crypto extends InvestmentAsset {
    @Column(name = "symbol")
    private String symbol;

    protected Crypto() {
    }

    public Crypto(
            User user,
            String name,
            String symbol
    ) {
        super(user, name, InvestmentType.CRYPTO);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
