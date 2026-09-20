package com.diploma.finance.income.entity.records;

import java.time.LocalDate;

public record MonthBoundaries(
        LocalDate startOfMonth,
        LocalDate startOfNextMonth
) {}
