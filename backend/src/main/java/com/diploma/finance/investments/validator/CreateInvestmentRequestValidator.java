package com.diploma.finance.investments.validator;

import com.diploma.finance.investments.dto.request.*;
import com.diploma.finance.investments.entity.enums.Currency;
import com.diploma.finance.investments.entity.enums.InvestmentType;

import java.math.BigDecimal;

public class CreateInvestmentRequestValidator {

    public static void validate(CreateInvestmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException(
                    "Investment request cannot be null"
            );
        }

        if (request.getUserId() == null) {
            throw new IllegalArgumentException(
                    "New asset should have an owner/user"
            );
        }

        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "New asset should have a name"
            );
        }

        if (request.getType() == null) {
            throw new IllegalArgumentException(
                    "New asset should have a type"
            );
        }

        validateSpecificRequest(request);
    }

    private static void validateSpecificRequest(
            CreateInvestmentRequest request
    ) {
        if (request instanceof CreateStockRequest stock) {
            validateStock(stock);
        } else if (request instanceof CreateEtfRequest etf) {
            validateEtf(etf);
        } else if (request instanceof CreateBondRequest bond) {
            validateBond(bond);
        } else if (request instanceof CreateCryptoRequest crypto) {
            validateCrypto(crypto);
        } else if (request instanceof CreateCommodityRequest commodity) {
            validateCommodity(commodity);
        } else if (request instanceof CreateCashRequest cash) {
            validateCash(cash);
        }
    }

    private static void validateStock(CreateStockRequest request) {
        if (!request.getType().equals(InvestmentType.STOCK)) {
            throw new IllegalArgumentException(
                    "Request has invalid ETF type"
            );
        }
        if (request.getTicker() == null || request.getTicker().isBlank()) {
            throw new IllegalArgumentException(
                    "Ticker is required for stock"
            );
        }
    }

    private static void validateEtf(CreateEtfRequest request) {
        if (!request.getType().equals(InvestmentType.ETF)) {
            throw new IllegalArgumentException(
                    "Request has invalid ETF type"
            );
        }
        if (request.getTicker() == null || request.getTicker().isBlank()) {
            throw new IllegalArgumentException(
                    "Ticker is required for ETF"
            );
        }

        if (request.getBrokerAccount() == null || request.getBrokerAccount().isBlank()) {
            throw new IllegalArgumentException(
                    "Broker account is required for ETF"
            );
        }
    }

    private static void validateCrypto(CreateCryptoRequest request) {
        if (!request.getType().equals(InvestmentType.CRYPTO)) {
            throw new IllegalArgumentException(
                    "Request has invalid crypto type"
            );
        }
        if (request.getSymbol() == null || request.getSymbol().isBlank()) {
            throw new IllegalArgumentException(
                    "Symbol is required for crypto"
            );
        }

        if (request.getWallet() == null || request.getWallet().isBlank()) {
            throw new IllegalArgumentException(
                    "Broker account is required for crypto"
            );
        }
    }

    private static void validateCommodity(CreateCommodityRequest request) {
        if (!request.getType().equals(InvestmentType.COMMODITY)) {
            throw new IllegalArgumentException(
                    "Request has invalid commodity type"
            );
        }
        if (request.getSymbol() == null || request.getSymbol().isBlank()) {
            throw new IllegalArgumentException(
                    "Symbol is required for commodity"
            );
        }
    }

    private static void validateBond(CreateBondRequest request) {
        if (!request.getType().equals(InvestmentType.BOND)) {
            throw new IllegalArgumentException(
                    "Request has invalid bond type"
            );
        }
        if (request.getIssuer() == null ||
                request.getIssuer().isBlank()) {

            throw new IllegalArgumentException(
                    "Issuer is required for bond"
            );
        }

        if (request.getFaceValue() == null ||
                request.getFaceValue().compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Bond face value must be greater than zero"
            );
        }

        if (request.getCouponRate() == null ||
                request.getCouponRate().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Bond coupon rate cannot be negative"
            );
        }

        if (request.getMaturityDate() == null) {
            throw new IllegalArgumentException(
                    "Maturity date is required for bond"
            );
        }
    }

    public static void validateCash(CreateCashRequest request) {
        if (!request.getType().equals(InvestmentType.CASH)) {
            throw new IllegalArgumentException(
                    "Request has invalid cash type"
            );
        }
        if (request.getCurrency() == null) {
            throw new IllegalArgumentException(
                    "Currency is required for cash"
            );
        }
        if (!request.getCurrency().equals(Currency.EUR)) {
            throw new IllegalArgumentException(
                    "Invalid cash currency"
            );
        }
    }
}