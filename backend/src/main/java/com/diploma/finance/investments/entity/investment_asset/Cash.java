package com.diploma.finance.investments.entity.investment_asset;

import com.diploma.finance.investments.entity.enums.Currency;
import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("CASH")
public class Cash extends InvestmentAsset {
    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    protected Cash() {
    }

    public Cash(
            User user,
            String investmentName,
            Currency currency
    ) {
        super(user, investmentName, InvestmentType.CASH);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }
}
