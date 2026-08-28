package com.diploma.finance.investments.entity.transaction;

import com.diploma.finance.investments.entity.enums.TransactionType;
import com.diploma.finance.investments.entity.investment_asset.InvestmentAsset;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "investment_transactions")
public class InvestmentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private InvestmentAsset asset;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false, length = 10)
    private TransactionType transactionType;

    @Column(nullable = false, precision = 28, scale = 10)
    private BigDecimal quantity;

    @Column(name = "price_per_unit", nullable = false, precision = 18, scale = 8)
    private BigDecimal pricePerUnit;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "wallet", nullable = false, length = 255)
    private String wallet;

    @Column(name = "broker_account", nullable = false, length = 100)
    private String brokerAccount;


    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected InvestmentTransaction() {}
    public InvestmentTransaction(
            InvestmentAsset asset,
            TransactionType transactionType,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate transactionDate,
            String wallet,
            String brokerAccount,
            String notes
    ) {
        this.asset = asset;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.transactionDate = transactionDate;
        this.wallet = wallet;
        this.brokerAccount = brokerAccount;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public InvestmentAsset getAsset() {
        return asset;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getWallet() {
        return wallet;
    }

    public String getBrokerAccount() {
        return brokerAccount;
    }


    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAsset(InvestmentAsset asset) {
        this.asset = asset;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
