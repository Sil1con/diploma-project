package com.diploma.finance.investments.mapper;

import com.diploma.finance.investments.dto.request.*;
import com.diploma.finance.investments.entity.investment_asset.InvestmentAsset;
import com.diploma.finance.investments.entity.transaction.InvestmentTransaction;

public final class TransactionRequestMapper {
    private TransactionRequestMapper() {}
    public static InvestmentTransaction toTransaction(CreateInvestmentRequest request, InvestmentAsset asset) {
        if (request instanceof CreateCryptoRequest cryptoRequest) {
            return new InvestmentTransaction(
                    asset,
                    cryptoRequest.getTransactionType(),
                    cryptoRequest.getQuantity(),
                    cryptoRequest.getPricePerUnit(),
                    cryptoRequest.getPurchaseDate(),
                    cryptoRequest.getWallet(),
                    null,
                    cryptoRequest.getNotes()
            );
        }

        if (request instanceof CreateEtfRequest etfRequest) {
            return new InvestmentTransaction(
                    asset,
                    etfRequest.getTransactionType(),
                    etfRequest.getQuantity(),
                    etfRequest.getPricePerUnit(),
                    etfRequest.getPurchaseDate(),
                    null,
                    etfRequest.getBrokerAccount(),
                    etfRequest.getNotes()
            );
        }

        if (request instanceof CreateStockRequest
                || request instanceof CreateCommodityRequest
                || request instanceof CreateBondRequest
                || request instanceof CreateCashRequest
        ) {
            return new InvestmentTransaction(
                    asset,
                    request.getTransactionType(),
                    request.getQuantity(),
                    request.getPricePerUnit(),
                    request.getPurchaseDate(),
                    null,
                    null,
                    request.getNotes()
            );
        }

        throw new IllegalArgumentException(
                "Unsupported transaction request type: "
                        + request.getClass().getSimpleName()
        );
    }
}
