package com.diploma.finance.income.entity;

import com.diploma.finance.income.entity.enums.IncomeCategory;
import com.diploma.finance.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "income_sources")
public class IncomeSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private IncomeCategory category;

    @Column(name = "income_date", nullable = false)
    private LocalDate incomeDate;

    @Column(name = "description")
    private String description;

    protected IncomeSource() {}

    public IncomeSource(
            User user,
            String name,
            BigDecimal amount,
            IncomeCategory category,
            LocalDate incomeDate,
            String description
    ) {
        this.user = user;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.incomeDate = incomeDate;
        this.description = description;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public IncomeCategory getCategory() {
        return category;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public String getDescription() {
        return description;
    }
}

