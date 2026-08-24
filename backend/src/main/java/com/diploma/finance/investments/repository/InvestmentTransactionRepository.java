package com.diploma.finance.investments.repository;

import com.diploma.finance.investments.entity.enums.TransactionType;
import com.diploma.finance.investments.entity.investment_transaction.InvestmentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvestmentTransactionRepository extends JpaRepository<InvestmentTransaction, Long> {
    @Override
    List<InvestmentTransaction> findAll();

    @Override
    List<InvestmentTransaction> findAllById(Iterable<Long> longs);

    @Override
    Optional<InvestmentTransaction> findById(Long id);

    @Override
    <S extends InvestmentTransaction> S save(S entity);

    void deleteById(Long aLong);

    List<InvestmentTransaction> findByAssetId(Long assetId);

    List<InvestmentTransaction> findByTransactionType(TransactionType transactionType);

    List<InvestmentTransaction> findByAssetIdOrderByTransactionDateDesc(Long assetId);
}