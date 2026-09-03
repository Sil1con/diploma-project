package com.diploma.finance.investments.service;

import com.diploma.finance.investments.dto.request.*;
import com.diploma.finance.investments.dto.response.summary.InvestmentSummaryResponse;
import com.diploma.finance.investments.dto.response.transaction.TransactionResponse;
import com.diploma.finance.investments.entity.enums.TransactionType;
import com.diploma.finance.investments.entity.investment_asset.*;
import com.diploma.finance.investments.entity.transaction.InvestmentTransaction;
import com.diploma.finance.investments.finder.ExistingInvestmentFinder;
import com.diploma.finance.investments.mapper.*;
import com.diploma.finance.investments.repository.transaction.InvestmentTransactionRepository;
import com.diploma.finance.investments.repository.investment.InvestmentsRepository;
import com.diploma.finance.investments.validator.CreateInvestmentRequestValidator;
import com.diploma.finance.investments.validator.CreateRequestTransactionValidator;
import com.diploma.finance.user.entity.User;
import com.diploma.finance.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InvestmentService {
    private final InvestmentsRepository investmentsRepository;
    private final ExistingInvestmentFinder existingInvestmentFinder;
    private final InvestmentTransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public InvestmentService(
            InvestmentsRepository investmentsRepository,
            ExistingInvestmentFinder existingInvestmentFinder,
            InvestmentTransactionRepository investmentTransactionRepository,
            UserRepository userRepository
    ) {
        this.investmentsRepository = investmentsRepository;
        this.existingInvestmentFinder = existingInvestmentFinder;
        this.transactionRepository = investmentTransactionRepository;
        this.userRepository = userRepository;
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
    }

    //Private methods of the class
    private Optional<InvestmentAsset> findExistingAsset(InvestmentAsset asset) {
        return existingInvestmentFinder.find(asset);
    }

    private InvestmentAsset createAssetFromRequest(CreateInvestmentRequest request) {
        CreateInvestmentRequestValidator.validate(request);

        User user = getUser(request.getUserId());

        return InvestmentRequestMapper.toAsset(request, user);
    }

//    private InvestmentTransaction createTransactionFromRequestAndAsset(CreateInvestmentRequest request, InvestmentAsset asset) {
//        CreateRequestTransactionValidator.validate(request);
//
//        return TransactionRequestMapper.toTransaction(request, asset);
//    }

    private InvestmentAsset resolveAsset(
            CreateInvestmentRequest request,
            InvestmentAsset incomingAsset
    ) {
        Optional<InvestmentAsset> existingAsset =
                findExistingAsset(incomingAsset);

        if (existingAsset.isPresent()) {
            return existingAsset.get();
        }

        if (request.getTransactionType() == TransactionType.SELL) {
            throw new IllegalArgumentException(
                    "Cannot sell an investment that is not in the portfolio"
            );
        }

        return investmentsRepository.save(incomingAsset);
    }

    public InvestmentAsset getInvestment(Long id) {
        return investmentsRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Investment not found")
                );
    }

    public List<InvestmentAsset> getInvestments(Long userId) {
        User user = getUser(userId);

        return investmentsRepository.findAllByUserId(user.getId());
    }

    public InvestmentTransaction getTransaction(Long assetId, Long transactionId) {
        InvestmentAsset asset = getInvestment(assetId);

        return transactionRepository.findByIdAndAssetIdAndAssetUserId(transactionId, assetId, asset.getUser().getId() )
                .orElseThrow(() ->
                        new RuntimeException("Transaction not found")
                );
    }

    public List<InvestmentTransaction> getAllTransactions(Long userId) {
        return transactionRepository.findAllByAssetUserId(userId);
    }

    public List<InvestmentTransaction> getTransactionsByAssetId(Long assetId, Long userId) {
        return transactionRepository.findAllByAssetIdAndAssetUserId(assetId, userId);
    }

    public List<InvestmentTransaction> getTransactionsByAssetIdAndTransactionType(Long assetId, TransactionType type) {
        return transactionRepository.findAllByAssetIdAndTransactionType(assetId, type);
    }

    @Transactional
    public TransactionResponse createInvestmentTransaction(CreateInvestmentRequest request) {
        CreateRequestTransactionValidator.validate(request);

        InvestmentAsset incomingAsset = createAssetFromRequest(request);

        InvestmentAsset asset = resolveAsset(request, incomingAsset);

        InvestmentTransaction transaction =
                TransactionRequestMapper.toTransaction(request, asset);

        transactionRepository.save(transaction);

        return TransactionResponseMapper.toResponseDto(transaction);
    }

    public BigDecimal calculatePortfolioTotalValue(Long userId) {
        User user = getUser(userId);
        BigDecimal totalValue = BigDecimal.ZERO;

        List<InvestmentSummaryResponse> responses = calculatePreviewSummary(user.getId());

        for (InvestmentSummaryResponse response : responses) {
            totalValue = totalValue.add(response.getTotalValue());
        }

        return totalValue;
    }

    public List<InvestmentSummaryResponse> calculatePreviewSummary(Long userId) {
        User user = getUser(userId);
        List<InvestmentSummaryResponse> summaryResponses = new ArrayList<>();

        List<InvestmentAsset> assets =
                investmentsRepository.findAllByUserId(user.getId());

        List<InvestmentTransaction> transactions =
                transactionRepository.findAllByAssetUserId(user.getId());

        for (InvestmentAsset asset : assets) {
            BigDecimal totalValue = calculateTotalAssetValue(asset, transactions);
            
            summaryResponses.add(InvestmentSummaryResponseMapper.toResponse(asset, totalValue));
        }

        sortSummaryResponses(summaryResponses);

        return summaryResponses;
    }

    private BigDecimal calculateTotalAssetValue(InvestmentAsset asset, List<InvestmentTransaction> transactions) {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (InvestmentTransaction transaction : transactions) {
            if (!Objects.equals(asset.getId(), transaction.getAsset().getId())) {
                continue;
            }

            if (transaction.getTransactionType() == TransactionType.BUY) {
                totalValue = totalValue.add(transaction.getQuantity().multiply(transaction.getPricePerUnit()));
            }

            if (transaction.getTransactionType() == TransactionType.SELL) {
                totalValue = totalValue.subtract(transaction.getQuantity().multiply(transaction.getPricePerUnit()));
            }
        }

        return totalValue;
    }

    private void sortSummaryResponses(List<InvestmentSummaryResponse> summaryResponses) {
        for (int i = 0; i < summaryResponses.size() - 1; i++) {
            for (int j = i + 1; j < summaryResponses.size(); j++) {

                if (summaryResponses.get(j).getTotalValue()
                        .compareTo(summaryResponses.get(i).getTotalValue()) > 0) {

                    InvestmentSummaryResponse temp = summaryResponses.get(i);

                    summaryResponses.set(i, summaryResponses.get(j));
                    summaryResponses.set(j, temp);
                }
            }
        }
    }
}
