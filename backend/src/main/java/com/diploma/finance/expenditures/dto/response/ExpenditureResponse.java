package com.diploma.finance.expenditures.dto.response;

import com.diploma.finance.expenditures.entity.enums.ExpenditureCategory;
import com.diploma.finance.expenditures.entity.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenditureResponse {
    private Long id;

    private String name;

    private ExpenditureCategory category;

    private BigDecimal amount;

    private LocalDate expenditureDate;

    private PaymentMethod paymentMethod;

    private String vendor;

    private String receiptPath;

    private String notes;

    public ExpenditureResponse(
            Long id,
            String name,
            ExpenditureCategory category,
            BigDecimal amount,
            LocalDate expenditureDate,
            PaymentMethod paymentMethod,
            String vendor,
            String receiptPath,
            String notes
    ) {
        this.id = id;
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
