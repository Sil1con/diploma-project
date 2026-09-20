package com.diploma.finance.expenditures.entity;

import com.diploma.finance.expenditures.entity.enums.ExpenditureCategory;
import com.diploma.finance.expenditures.entity.enums.PaymentMethod;
import com.diploma.finance.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenditures")
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ExpenditureCategory category;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "expenditure_date", nullable = false)
    private LocalDate expenditureDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Column(name = "receipt_path")
    private String receiptPath;

    @Column(name = "notes")
    private String notes;

    protected Expenditure() {}

    public Expenditure(
            User user,
            String name,
            ExpenditureCategory category,
            BigDecimal amount,
            LocalDate expenditureDate,
            PaymentMethod paymentMethod,
            String vendor,
            String receiptPath,
            String notes
    ) {
        this.user = user;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.expenditureDate = expenditureDate;
        this.paymentMethod = paymentMethod;
        this.vendor = vendor;
        this.receiptPath = receiptPath;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public ExpenditureCategory getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getExpenditureDate() {
        return expenditureDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getVendor() {
        return vendor;
    }

    public String getReceiptPath() {
        return receiptPath;
    }

    public String getNotes() {
        return notes;
    }
}
