package com.diploma.finance.income.mapper;

import com.diploma.finance.income.dto.IncomeResponse;
import com.diploma.finance.income.entity.IncomeSource;

import java.util.ArrayList;
import java.util.List;

public class IncomeResponseMapper {
    public static IncomeResponse toResponse(IncomeSource incomeSource) {
        return new IncomeResponse(
                incomeSource.getId(),
                incomeSource.getName(),
                incomeSource.getAmount(),
                incomeSource.getCategory(),
                incomeSource.getIncomeDate(),
                incomeSource.getDescription()
        );
    }

    public static List<IncomeResponse> toResponses(List<IncomeSource> incomeSources) {
        List<IncomeResponse> incomeResponses = new ArrayList<>();

        for (IncomeSource incomeSource : incomeSources) {
            IncomeResponse response = new IncomeResponse(
                    incomeSource.getId(),
                    incomeSource.getName(),
                    incomeSource.getAmount(),
                    incomeSource.getCategory(),
                    incomeSource.getIncomeDate(),
                    incomeSource.getDescription()
            );

            incomeResponses.add(response);
        }

        return incomeResponses;
    }
}
