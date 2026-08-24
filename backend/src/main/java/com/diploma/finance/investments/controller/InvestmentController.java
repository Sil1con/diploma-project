package com.diploma.finance.investments.controller;

import com.diploma.finance.investments.dto.request.CreateInvestmentRequest;
import com.diploma.finance.investments.dto.response.investment.InvestmentResponse;
import com.diploma.finance.investments.entity.investment_asset.InvestmentAsset;
import com.diploma.finance.investments.entity.investment_transaction.InvestmentTransaction;
import com.diploma.finance.investments.mapper.InvestmentResponseMapper;
import com.diploma.finance.investments.service.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
    private final InvestmentService investmentService;

    public InvestmentController(
            InvestmentService investmentService
    ) {
        this.investmentService = investmentService;
    }

    @PostMapping
    public InvestmentResponse createInvestment(
            @Valid @RequestBody CreateInvestmentRequest request
    ) {
        return investmentService.createInvestment(request);
    }

    @GetMapping
    public List<InvestmentAsset> getInvestments() {
        return investmentService.getInvestmentAssets();
    }

    @GetMapping("/{id}")
    public InvestmentResponse getInvestment(@PathVariable Long id) {
        InvestmentAsset asset = investmentService.getInvestment(id);

        return InvestmentResponseMapper.toResponseDto(asset);
    }

//    @GetMapping("/{id}/transaction")
//    public InvestmentTransaction getTransaction(@PathVariable Long transactionId) {
//        return investmentService.getTransaction(transactionId);
//    }

    @GetMapping("/{id}/transactions")
    public List<InvestmentTransaction> getTransactions(@PathVariable Long id) {
        return investmentService.getTransactionsByAsset(id);
    }
}
