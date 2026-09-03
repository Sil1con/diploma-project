package com.diploma.finance.investments.validator;

import com.diploma.finance.exception.entity.request.InvalidRequestException;
import com.diploma.finance.investments.dto.request.CreateCryptoRequest;
import com.diploma.finance.investments.dto.request.CreateEtfRequest;
import com.diploma.finance.investments.dto.request.CreateInvestmentRequest;
import com.diploma.finance.investments.entity.enums.TransactionType;

import java.math.BigDecimal;

public final class CreateRequestTransactionValidator {
    private CreateRequestTransactionValidator() {}
    public static void validate(CreateInvestmentRequest request) {
        if (request == null) {
            throw new InvalidRequestException(
                    "Transaction request cannot be null"
            );
        }

        if (request.getTransactionType() == null) {
            throw new InvalidRequestException(
                    "Transaction type cannot be null"
            );
        }

        if (request.getQuantity() == null) {
            throw new InvalidRequestException(
                    "Transaction quantity cannot be null"
            );
        }

        if (request.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidRequestException(
                    "Transaction quantity must be greater than zero"
            );
        }

        if (request.getPricePerUnit() == null) {
            throw new InvalidRequestException(
                    "Transaction price per unit cannot be null"
            );
        }

        if (request.getPricePerUnit().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidRequestException(
                    "Transaction price per unit must be greater than zero"
            );
        }

        if (request.getPurchaseDate() == null) {
            throw new InvalidRequestException(
                    "Purchase date is required for Transaction"
            );
        }

        validateSpecificRequest(request);
    }

    private static void validateSpecificRequest(
            CreateInvestmentRequest request
    ) {
        if (request instanceof CreateCryptoRequest cryptoRequest) {
            validateCrypto(cryptoRequest);
        } else if (request instanceof CreateEtfRequest etfRequest) {
            validateEtf(etfRequest);
        }
    }

    private static void validateCrypto(CreateCryptoRequest request) {
        if (request.getWallet() == null || request.getWallet().isBlank()) {
            throw new InvalidRequestException(
                    "Information about crypto wallet is required for Crypto transaction"
            );
        }
    }

    private static void validateEtf(CreateEtfRequest request) {
        if (request.getBrokerAccount() == null || request.getBrokerAccount().isBlank()) {
            throw new InvalidRequestException(
                    "Information about broker account is required for ETF transaction"
            );
        }
    }
}
