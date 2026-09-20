package com.diploma.finance.expenditures.dto.request;

import com.diploma.finance.expenditures.entity.enums.ExpenditureCategory;
import com.diploma.finance.expenditures.entity.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateExpenditureRequest {
    @NotNull
    private Long userId;

    @NotNull
    private String name;

    @NotNull
    private ExpenditureCategory category;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private LocalDate expenditureDate;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private String vendor;

    private MultipartFile receipt;

    private String notes;

    public CreateExpenditureRequest(
            Long userId,
            String name,
            ExpenditureCategory category,
            BigDecimal amount,
            LocalDate expenditureDate,
            PaymentMethod paymentMethod,
            String vendor,
            MultipartFile receipt,
            String notes
    ) {
        this.userId = userId;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.expenditureDate = expenditureDate;
        this.paymentMethod = paymentMethod;
        this.vendor = vendor;
        this.receipt = receipt;
        this.notes = notes;
    }

    public Long getUserId() {
        return userId;
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

    public MultipartFile getReceipt() {
        return receipt;
    }

    public String getNotes() {
        return notes;
    }

    public void setReceipt(MultipartFile receipt) {
        this.receipt = receipt;
    }
}
