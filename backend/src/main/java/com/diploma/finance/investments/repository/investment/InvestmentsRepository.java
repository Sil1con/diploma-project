package com.diploma.finance.investments.repository.investment;

import com.diploma.finance.investments.entity.investment_asset.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentsRepository extends JpaRepository<InvestmentAsset, Long> {
    List<InvestmentAsset> findAllByUserId(Long userId);
}
