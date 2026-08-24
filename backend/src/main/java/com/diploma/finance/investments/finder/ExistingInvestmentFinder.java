package com.diploma.finance.investments.finder;

import com.diploma.finance.investments.entity.investment_asset.*;
import com.diploma.finance.investments.repository.investment.*;
import com.diploma.finance.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistingInvestmentFinder {

    private final StockRepository stockRepository;
    private final EtfRepository etfRepository;
    private final CryptoRepository cryptoRepository;
    private final CommodityRepository commodityRepository;
    private final BondRepository bondRepository;
    private final CashRepository cashRepository;

    public ExistingInvestmentFinder(
            StockRepository stockRepository,
            EtfRepository etfRepository,
            CryptoRepository cryptoRepository,
            CommodityRepository commodityRepository,
            BondRepository bondRepository,
            CashRepository cashRepository
    ) {
        this.stockRepository = stockRepository;
        this.etfRepository = etfRepository;
        this.cryptoRepository = cryptoRepository;
        this.commodityRepository = commodityRepository;
        this.bondRepository = bondRepository;
        this.cashRepository = cashRepository;
    }

    public Optional<InvestmentAsset> find(InvestmentAsset asset) {
        if (asset instanceof Stock stock) {
            return stockRepository
                    .findByUserAndTicker(stock.getUser(), stock.getTicker())
                    .map(existingAsset -> existingAsset);
        }

        if (asset instanceof ETF etf) {
            return etfRepository
                    .findByUserAndTicker(etf.getUser(), etf.getTicker())
                    .map(existingAsset -> existingAsset);
        }

        if (asset instanceof Crypto crypto) {
            return cryptoRepository
                    .findByUserAndSymbol(crypto.getUser(), crypto.getSymbol())
                    .map(existingAsset -> existingAsset);
        }

        if (asset instanceof Commodity commodity) {
            return commodityRepository
                    .findByUserAndSymbol(commodity.getUser(), commodity.getSymbol())
                    .map(existingAsset -> existingAsset);
        }

        if (asset instanceof Bond bond) {
            return bondRepository
                    .findByUserAndIsin(bond.getUser(), bond.getIsin())
                    .map(existingAsset -> existingAsset);
        }

        if (asset instanceof Cash cash) {
            return cashRepository
                    .findByUserAndCurrency(cash.getUser(), cash.getCurrency())
                    .map(existingAsset -> existingAsset);
        }

        throw new IllegalArgumentException(
                "Unsupported investment type: " + asset.getType()
        );
    }
}