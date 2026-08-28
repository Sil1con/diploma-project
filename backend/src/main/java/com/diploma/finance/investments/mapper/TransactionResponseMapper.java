package com.diploma.finance.investments.mapper;

import com.diploma.finance.investments.dto.response.investment.InvestmentResponse;
import com.diploma.finance.investments.dto.response.transaction.*;
import com.diploma.finance.investments.entity.investment_asset.*;
import com.diploma.finance.investments.entity.transaction.InvestmentTransaction;

public class TransactionResponseMapper {
    public static TransactionResponse toResponseDto(InvestmentTransaction transaction) {

        if (transaction.getAsset() instanceof Stock stock) {
            return new StockTransactionResponse(
                    stock.getId(),
                    transaction.getId(),
                    stock.getInvestmentName(),
                    transaction.getTransactionType(),
                    transaction.getQuantity(),
                    transaction.getPricePerUnit(),
                    transaction.getTransactionDate(),
                    transaction.getNotes(),
                    stock.getTicker()
            );
        }

        if (transaction.getAsset() instanceof ETF etf) {
            return new EtfTransactionResponse(
                    etf.getId(),
                    transaction.getId(),
                    etf.getInvestmentName(),
                    transaction.getTransactionType(),
                    transaction.getQuantity(),
                    transaction.getPricePerUnit(),
                    transaction.getTransactionDate(),
                    transaction.getNotes(),
                    etf.getTicker(),
                    transaction.getBrokerAccount()
            );
        }

        if (transaction.getAsset() instanceof Crypto crypto) {
            return new CryptoTransactionResponse(
                    crypto.getId(),
                    transaction.getId(),
                    crypto.getInvestmentName(),
                    transaction.getTransactionType(),
                    transaction.getQuantity(),
                    transaction.getPricePerUnit(),
                    transaction.getTransactionDate(),
                    transaction.getNotes(),
                    crypto.getSymbol(),
                    transaction.getWallet()
            );
        }

        if (transaction.getAsset() instanceof Commodity commodity) {
            return new CommodityTransactionResponse(
                    commodity.getId(),
                    transaction.getId(),
                    commodity.getInvestmentName(),
                    transaction.getTransactionType(),
                    transaction.getQuantity(),
                    transaction.getPricePerUnit(),
                    transaction.getTransactionDate(),
                    transaction.getNotes(),
                    commodity.getSymbol()
            );
        }

        if (transaction.getAsset() instanceof Bond bond) {
            return new BondTransactionResponse(
                    bond.getId(),
                    transaction.getId(),
                    bond.getInvestmentName(),
                    transaction.getTransactionType(),
                    transaction.getQuantity(),
                    transaction.getPricePerUnit(),
                    transaction.getTransactionDate(),
                    transaction.getNotes()
            );
        }

        if (transaction.getAsset() instanceof Cash cash) {
            return new CashTransactionResponse(
                    cash.getId(),
                    transaction.getId(),
                    cash.getInvestmentName(),
                    transaction.getTransactionType(),
                    transaction.getQuantity(),
                    transaction.getPricePerUnit(),
                    transaction.getTransactionDate(),
                    transaction.getNotes(),
                    cash.getCurrency()
            );
        }

        throw new IllegalArgumentException(
                "Unsupported investment type: " + transaction.getClass().getSimpleName()
        );
    }

}
