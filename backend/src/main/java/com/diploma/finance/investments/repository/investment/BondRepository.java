package com.diploma.finance.investments.repository.investment;

import com.diploma.finance.investments.entity.investment_asset.Bond;
import com.diploma.finance.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BondRepository
        extends JpaRepository<Bond, Long> {

    Optional<Bond> findByUserAndIsin(User user, String isin);
}