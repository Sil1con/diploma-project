package com.diploma.finance.investments.repository.investment;

import com.diploma.finance.investments.entity.investment_asset.Crypto;
import com.diploma.finance.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoRepository
        extends JpaRepository<Crypto, Long> {

    Optional<Crypto> findByUserAndSymbol(User user, String symbol);
}
