package com.diploma.finance.investments.entity.investment_asset;

import com.diploma.finance.investments.entity.enums.Currency;
import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "investment_assets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class InvestmentAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "investment_name")
    private String investmentName;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            insertable = false,
            updatable = false
    )
    private InvestmentType type;

    protected InvestmentAsset() {
    }

    protected InvestmentAsset(
           User user,
           String investmentName,
           InvestmentType type
    ) {
        this.user = user;
        this.investmentName = investmentName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public InvestmentType getType() {
        return type;
    };

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }
}