package com.diploma.finance.investments.mapper;

import com.diploma.finance.investments.dto.response.summary.InvestmentSummaryResponse;
import com.diploma.finance.investments.entity.investment_asset.InvestmentAsset;

import java.math.BigDecimal;

public class InvestmentSummaryResponseMapper {
    public static InvestmentSummaryResponse toResponse(InvestmentAsset asset, BigDecimal totalValue) {
        return new InvestmentSummaryResponse(
                asset.getId(),
                asset.getInvestmentName(),
                asset.getType(),
                totalValue
        );
    }
}
