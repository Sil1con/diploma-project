package com.diploma.finance.utilities.record;

import java.time.LocalDate;

public record PeriodBoundaries(
        LocalDate startOfPeriod,
        LocalDate endOfPeriod
) {}
