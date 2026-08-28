package com.diploma.finance.investments.service;

import com.diploma.finance.investments.dto.request.*;
import com.diploma.finance.investments.dto.response.investment.InvestmentResponse;
import com.diploma.finance.investments.dto.response.transaction.TransactionResponse;
import com.diploma.finance.investments.entity.enums.TransactionType;
import com.diploma.finance.investments.entity.investment_asset.*;
import com.diploma.finance.investments.entity.transaction.InvestmentTransaction;
import com.diploma.finance.investments.finder.ExistingInvestmentFinder;
import com.diploma.finance.investments.mapper.InvestmentRequestMapper;
import com.diploma.finance.investments.mapper.InvestmentResponseMapper;
import com.diploma.finance.investments.mapper.TransactionRequestMapper;
import com.diploma.finance.investments.mapper.TransactionResponseMapper;
import com.diploma.finance.investments.repository.transaction.InvestmentTransactionRepository;
import com.diploma.finance.investments.repository.investment.InvestmentsRepository;
import com.diploma.finance.investments.validator.CreateInvestmentRequestValidator;
import com.diploma.finance.investments.validator.CreateRequestTransactionValidator;
import com.diploma.finance.user.entity.User;
import com.diploma.finance.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
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

    public List<InvestmentAsset> getInvestmentAssets() {
        return investmentsRepository.findAll();
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

//    @Transactional
//    public InvestmentTransaction addTransaction(InvestmentTransaction transaction) {
//        if (transaction.getId() != null) {
//            throw new RuntimeException("New transaction should not already have an ID");
//        }
//
//        if (transaction.getAsset() == null ||
//                transaction.getAsset().getId() == null) {
//            throw new RuntimeException("Investment asset is required");
//        }
//
//        Long assetId = transaction.getAsset().getId();
//        User user = getUser(transaction.getAsset().getUser().getId());
//
//        InvestmentAsset asset = investmentsRepository.findById(assetId)
//                .orElseThrow(() ->
//                        new RuntimeException("Investment not found")
//                );
//
//        transaction.setAsset(asset);
//
//        if (transaction.getQuantity() == null ||
//                transaction.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
//            throw new RuntimeException(
//                    "Investment quantity must be greater than zero"
//            );
//        }
//
//        if (transaction.getPricePerUnit() == null ||
//                transaction.getPricePerUnit().compareTo(BigDecimal.ZERO) <= 0) {
//
//            throw new RuntimeException(
//                    "Price per unit must be greater than zero"
//            );
//        }
//
//        if (transaction.getTransactionType() == null) {
//            throw new RuntimeException("Transaction type is required");
//        }
//
//        if (transaction.getTransactionType() != TransactionType.BUY &&
//                transaction.getTransactionType() != TransactionType.SELL) {
//
//            throw new RuntimeException("Invalid transaction type");
//        }
//
//        if (transaction.getTransactionType() == TransactionType.SELL) {
//            BigDecimal currentlyOwned =
//                    calculateCurrentQuantity(user.getId(), assetId);
//
//            if (transaction.getQuantity().compareTo(currentlyOwned) > 0) {
//                throw new RuntimeException(
//                        "Cannot sell more than currently owned"
//                );
//            }
//        }
//
//        return transactionRepository.save(transaction);
//    }

    private BigDecimal calculateCurrentQuantity(Long userId, Long assetId) {

        List<InvestmentTransaction> transactions =
                transactionRepository.findAllByAssetIdAndAssetUserId(assetId, userId);

        BigDecimal quantity = BigDecimal.ZERO;

        for (InvestmentTransaction transaction : transactions) {

            if (transaction.getTransactionType() == TransactionType.BUY) {
                quantity = quantity.add(transaction.getQuantity());
            }

            if (transaction.getTransactionType() == TransactionType.SELL) {
                quantity = quantity.subtract(transaction.getQuantity());
            }
        }

        return quantity;
    }
}
