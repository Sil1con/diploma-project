package com.diploma.finance.expenditures.controller;

import com.diploma.finance.expenditures.dto.request.CreateExpenditureRequest;
import com.diploma.finance.expenditures.dto.response.CategorySummaryResponse;
import com.diploma.finance.expenditures.dto.response.ExpenditureResponse;
import com.diploma.finance.expenditures.service.ExpenditureService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/expenditures")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenditureController {
    private final ExpenditureService expenditureService;
    public ExpenditureController(
            ExpenditureService expenditureService
    ) {
        this.expenditureService = expenditureService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ExpenditureResponse createExpenditure(
            @RequestPart("expenditure") CreateExpenditureRequest request,
            @RequestPart(value = "receipt", required = false) MultipartFile receipt
    ) {
        return expenditureService.createExpenditure(request, receipt);
    }

    @GetMapping("/current-month/{userId}")
    public List<ExpenditureResponse> getCurrentMonthExpenditures(@PathVariable Long userId) {
        return expenditureService.getCurrentMonthExpenditures(userId);
    }

    @GetMapping("/{userId}/period")
    public List<ExpenditureResponse> getExpendituresForPeriod(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return expenditureService.getExpendituresForPeriod(
                userId,
                startDate,
                endDate
        );
    }

    @GetMapping("/current-month-summary/{userId}")
    public List<CategorySummaryResponse> getCurrentMonthCategoriesSummary(@PathVariable Long userId) {
        return expenditureService.getCurrentMonthCategoriesSummary(userId);
    }

    @GetMapping("/{userId}/period/summary")
    public List<CategorySummaryResponse> getCategoriesSummaryForPeriod(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return expenditureService.getCategoriesSummaryForPeriod(
                userId,
                startDate,
                endDate
        );
    }

    @DeleteMapping("/{userId}/delete/{expenditureId}")
    public ResponseEntity<Void> deleteExpenditure(@PathVariable Long userId, @PathVariable Long expenditureId) {
        expenditureService.deleteExpenditure(userId, expenditureId);

        return ResponseEntity.noContent().build();
    }
}