package com.diploma.finance.investments.repository.investment;

import com.diploma.finance.investments.entity.investment_asset.Commodity;
import com.diploma.finance.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommodityRepository
        extends JpaRepository<Commodity, Long> {

    Optional<Commodity> findByUserAndSymbol(User user, String symbol);
}