package com.diploma.finance.investments.dto.response.transaction;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BondTransactionResponse extends TransactionResponse {

    public BondTransactionResponse(
            Long assetId,
            Long transactionId,
            String investmentName,
            TransactionType transactionType,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate transactionDate,
            String notes
    ) {
        super(
                assetId,
                transactionId,
                investmentName,
                InvestmentType.BOND,
                transactionType, quantity,
                pricePerUnit,
                transactionDate,
                notes
        );
    }
}