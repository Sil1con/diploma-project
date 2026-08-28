package com.diploma.finance.investments.mapper;

import com.diploma.finance.investments.dto.response.investment.*;
import com.diploma.finance.investments.entity.investment_asset.*;
import com.diploma.finance.investments.entity.transaction.InvestmentTransaction;

public class InvestmentResponseMapper {
    public static InvestmentResponse toResponseDto(InvestmentAsset asset) {

        if (asset instanceof Stock stock) {
            return new StockResponse(
                    stock.getId(),
                    stock.getInvestmentName(),
                    stock.getTicker()
            );
        }

        if (asset instanceof ETF etf) {
            return new EtfResponse(
                    etf.getId(),
                    etf.getInvestmentName(),
                    etf.getTicker()
            );
        }

        if (asset instanceof Crypto crypto) {
            return new CryptoResponse(
                    crypto.getId(),
                    crypto.getInvestmentName(),
                    crypto.getSymbol()
            );
        }

        if (asset instanceof Commodity commodity) {
            return new CommodityResponse(
                    commodity.getId(),
                    commodity.getInvestmentName(),
                    commodity.getSymbol()
            );
        }

        if (asset instanceof Bond bond) {
            return new BondResponse(
                    bond.getId(),
                    bond.getInvestmentName(),
                    bond.getIssuer(),
                    bond.getIsin(),
                    bond.getFaceValue(),
                    bond.getCouponRate(),
                    bond.getMaturityDate()
            );
        }

        if (asset instanceof Cash cash) {
            return new CashResponse(
                    cash.getId(),
                    cash.getInvestmentName(),
                    cash.getCurrency()
            );
        }

        throw new IllegalArgumentException(
                "Unsupported investment type: " + asset.getClass().getSimpleName()
        );
    }

}
