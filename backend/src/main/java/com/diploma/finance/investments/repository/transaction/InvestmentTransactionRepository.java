package com.diploma.finance.investments.repository.transaction;

import com.diploma.finance.investments.entity.enums.TransactionType;
import com.diploma.finance.investments.entity.transaction.InvestmentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvestmentTransactionRepository extends JpaRepository<InvestmentTransaction, Long> {
    Optional<InvestmentTransaction> findByIdAndAssetIdAndAssetUserId(
            Long transactionId,
            Long assetId,
            Long userId
    );
    List<InvestmentTransaction> findAllByAssetUserId(Long userId);
    List<InvestmentTransaction> findAllByAssetIdAndAssetUserId(Long assetId, Long userId);

    List<InvestmentTransaction> findAllByAssetIdAndTransactionType(Long assetId, TransactionType transactionType);

    List<InvestmentTransaction> findByAssetIdOrderByTransactionDateDesc(Long assetId);

    void deleteById(Long aLong);
}