package com.diploma.finance.expenditures.finder;

import com.diploma.finance.expenditures.entity.Expenditure;
import com.diploma.finance.expenditures.repository.ExpenditureRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistingExpenditureFinder {
    private ExpenditureRepository expenditureRepository;

    public ExistingExpenditureFinder(
        ExpenditureRepository expenditureRepository
    ) {
        this.expenditureRepository = expenditureRepository;
    }

    public boolean find(Expenditure expenditure) {
        return expenditureRepository
            .existsByUserIdAndNameAndCategoryAndAmountAndExpenditureDateAndPaymentMethodAndVendor(
                    expenditure.getUser().getId(),
                    expenditure.getName(),
                    expenditure.getCategory(),
                    expenditure.getAmount(),
                    expenditure.getExpenditureDate(),
                    expenditure.getPaymentMethod(),
                    expenditure.getVendor()
            );
    }

    public Optional<Expenditure> findById(Long userId, Long expenditureId) {
        return this.expenditureRepository.findByUserIdAndId(userId, expenditureId);
    }
}
