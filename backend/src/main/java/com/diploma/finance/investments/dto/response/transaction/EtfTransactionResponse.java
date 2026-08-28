package com.diploma.finance.investments.dto.response.transaction;

import com.diploma.finance.investments.entity.enums.InvestmentType;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EtfTransactionResponse extends TransactionResponse {
    private String ticker;
    private String brokerAccount;
    public EtfTransactionResponse(
            Long assetId,
            Long transactionId,
            String investmentName,
            TransactionType transactionType,
            BigDecimal quantity,
            BigDecimal pricePerUnit,
            LocalDate transactionDate,
            String notes,
            String ticker,
            String brokerAccount
    ) {
        super(
                assetId,
                transactionId,
                investmentName,
                InvestmentType.ETF,
                transactionType, quantity,
                pricePerUnit,
                transactionDate,
                notes
        );
        this.ticker = ticker;
        this.brokerAccount = brokerAccount;
    }

    public String getTicker() {
        return ticker;
    }

    public String getBrokerAccount() {
        return brokerAccount;
    }
}
