package com.diploma.finance.income.mapper;

import com.diploma.finance.income.dto.request.CreateIncomeRequest;
import com.diploma.finance.income.entity.IncomeSource;
import com.diploma.finance.user.entity.User;

public class IncomeRequestMapper {
    public static IncomeSource toIncome(CreateIncomeRequest request, User user) {
        return new IncomeSource(
                user,
                request.getName(),
                request.getAmount(),
                request.getCategory(),
                request.getIncomeDate(),
                request.getDescription()
        );
    }
}
