package com.diploma.finance.investments.repository.investment;

import com.diploma.finance.investments.entity.enums.Currency;
import com.diploma.finance.investments.entity.investment_asset.Cash;
import com.diploma.finance.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CashRepository
        extends JpaRepository<Cash, Long> {

    Optional<Cash> findByUserAndCurrency(User user, Currency currency);
}
