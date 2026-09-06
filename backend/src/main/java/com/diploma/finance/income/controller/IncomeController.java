package com.diploma.finance.income.controller;

import com.diploma.finance.income.dto.CreateIncomeRequest;
import com.diploma.finance.income.dto.IncomeResponse;
import com.diploma.finance.income.service.IncomeService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin(origins = "http://localhost:4200")
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    public IncomeResponse createIncomeSource(
            @Valid @RequestBody CreateIncomeRequest request
    ) {
        return incomeService.createIncomeSource(request);
    }

    @GetMapping("/{userId}/current-month")
    public List<IncomeResponse> getCurrentMonthIncomeSources(
            @PathVariable Long userId
    ) {
        return incomeService.getCurrentMonthIncomeSources(userId);
    }

    @GetMapping("/{userId}")
    public List<IncomeResponse> getIncomeSourcesForMonth(
            @PathVariable Long userId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        YearMonth yearMonth = YearMonth.of(year, month);

        return incomeService.getIncomeSourcesForMonth(userId, yearMonth);
    }

    @GetMapping("/{userId}/current-total")
    public BigDecimal getCurrentMonthTotalIncome(@PathVariable Long userId) {
        return incomeService.calculateCurrentIncomeTotalValue(userId);
    }

    @GetMapping("/{userId}/total")
    public BigDecimal getTotalIncomeForMonth(
            @PathVariable Long userId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return incomeService.calculateCurrentIncomeTotalValue(userId);
    }
}
