package com.diploma.finance.income.validator;

import com.diploma.finance.exception.entity.request.InvalidRequestException;
import com.diploma.finance.income.dto.CreateIncomeRequest;

import java.math.BigDecimal;

public class CreateIncomeRequestValidator {
    public static void validate(CreateIncomeRequest request) {
        if (request == null) {
            throw new InvalidRequestException(
                    "Income request cannot be null"
            );
        }

        if (request.getUserId() == null) {
            throw new InvalidRequestException(
                    "New income source should have an owner/user"
            );
        }

        if (request.getName() == null || request.getName().isBlank()) {
            throw new InvalidRequestException(
                    "New income source should have a name"
            );
        }

        if (request.getAmount() == null) {
            throw new InvalidRequestException(
                    "Income source amount cannot be null"
            );
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidRequestException(
                    "Income source amount must be greater than zero"
            );
        }

        if (request.getCategory() == null) {
            throw new InvalidRequestException(
                    "New income source should have a category"
            );
        }

        if (request.getIncomeDate() == null) {
            throw new InvalidRequestException(
                    "New income source should have an income date"
            );
        }
    }
}
