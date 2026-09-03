package com.diploma.finance.investments.controller;

import com.diploma.finance.investments.dto.request.CreateInvestmentRequest;
import com.diploma.finance.investments.dto.response.investment.InvestmentResponse;
import com.diploma.finance.investments.dto.response.summary.InvestmentSummaryResponse;
import com.diploma.finance.investments.dto.response.transaction.TransactionResponse;
import com.diploma.finance.investments.entity.investment_asset.InvestmentAsset;
import com.diploma.finance.investments.entity.transaction.InvestmentTransaction;
import com.diploma.finance.investments.mapper.InvestmentResponseMapper;
import com.diploma.finance.investments.mapper.TransactionResponseMapper;
import com.diploma.finance.investments.service.InvestmentService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/investments")
@CrossOrigin(origins = "http://localhost:4200")
public class InvestmentController {
    private final InvestmentService investmentService;

    public InvestmentController(
            InvestmentService investmentService
    ) {
        this.investmentService = investmentService;
    }

    @PostMapping
    public TransactionResponse createInvestment(
            @Valid @RequestBody CreateInvestmentRequest request
    ) {
        return investmentService.createInvestmentTransaction(request);
    }

    @GetMapping("/{userId}")
    public List<InvestmentResponse> getInvestments(@PathVariable Long userId) {
        List <InvestmentAsset> investments = investmentService.getInvestments(userId);
        List <InvestmentResponse> investmentResponses = new ArrayList<>();

        for (InvestmentAsset asset : investments) {
            investmentResponses.add(InvestmentResponseMapper.toResponseDto(asset));
        }

        return investmentResponses;
    }

    @GetMapping("/preview/{userId}")
    public List<InvestmentSummaryResponse> getPreviewSummary(@PathVariable Long userId) {
        return investmentService.calculatePreviewSummary(userId);
    }

    @GetMapping("/overview/{userId}")
    public BigDecimal getPortfolioTotalValue(@PathVariable Long userId) {
        return investmentService.calculatePortfolioTotalValue(userId);
    }

    @GetMapping("/{assetId}/transaction/{transactionId}")
    public TransactionResponse getTransaction(
            @PathVariable Long assetId,
            @PathVariable Long transactionId
    ) {
        InvestmentTransaction transaction = investmentService.getTransaction(assetId, transactionId);

        return TransactionResponseMapper.toResponseDto(transaction);
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionResponse> getAssetTransactions(@PathVariable Long id) {
        List <InvestmentTransaction> transactions = investmentService.getAllTransactions(id);
        List <TransactionResponse> transactionResponses = new ArrayList<>();

        for (InvestmentTransaction trans : transactions) {
            transactionResponses.add(TransactionResponseMapper.toResponseDto(trans));
        }

        return transactionResponses;
    }
}
