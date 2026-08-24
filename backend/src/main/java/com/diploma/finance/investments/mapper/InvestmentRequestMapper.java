package com.diploma.finance.investments.mapper;

import com.diploma.finance.investments.dto.request.*;
import com.diploma.finance.investments.entity.investment_asset.*;
import com.diploma.finance.user.entity.User;

public class InvestmentRequestMapper {
    public static InvestmentAsset toAsset(CreateInvestmentRequest request, User user) {
        if (request instanceof CreateStockRequest stockRequest) {
            return new Stock(
                    user,
                    stockRequest.getName(),
                    stockRequest.getTicker().trim().toUpperCase()
            );
        }

        if (request instanceof CreateEtfRequest etfRequest) {
            return new ETF(
                    user,
                    etfRequest.getName(),
                    etfRequest.getTicker().trim().toUpperCase()
            );
        }

        if (request instanceof CreateCryptoRequest cryptoRequest) {
            return new Crypto(
                    user,
                    cryptoRequest.getName(),
                    cryptoRequest.getSymbol().trim().toUpperCase()
            );
        }

        if (request instanceof CreateCommodityRequest commodityRequest) {
            return new Commodity(
                    user,
                    commodityRequest.getName(),
                    commodityRequest.getSymbol().trim().toUpperCase()
            );
        }

        if (request instanceof CreateBondRequest bondRequest) {
            return new Bond(
                    user,
                    bondRequest.getName(),
                    bondRequest.getIssuer(),
                    bondRequest.getIsin().trim().toUpperCase(),
                    bondRequest.getFaceValue(),
                    bondRequest.getCouponRate(),
                    bondRequest.getMaturityDate()
            );
        }

        if (request instanceof CreateCashRequest cashRequest) {
            return new Cash(
                    user,
                    cashRequest.getName(),
                    cashRequest.getCurrency()
            );
        }

        throw new IllegalArgumentException(
                "Unsupported investment request type: "
                        + request.getClass().getSimpleName()
        );
    }
}
