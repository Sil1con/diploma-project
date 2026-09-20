package com.diploma.finance.expenditures.validator;

import com.diploma.finance.utilities.exception.entity.request.InvalidRequestException;

import java.time.LocalDate;

public class DatesValidator {
    private DatesValidator() {}
    public static void validate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new InvalidRequestException(
                    "Start date or end date cannot be null"
            );
        }

        if (startDate.isAfter(endDate)) {
            throw new InvalidRequestException(
                    "Start date cannot be later than end date"
            );
        }
    }
}
