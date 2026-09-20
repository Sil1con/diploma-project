package com.diploma.finance.expenditures.mapper;

import com.diploma.finance.expenditures.dto.response.ExpenditureResponse;
import com.diploma.finance.expenditures.entity.Expenditure;

import java.util.ArrayList;
import java.util.List;

public class ExpenditureResponseMapper {
    public static ExpenditureResponse toResponse(Expenditure expenditure) {
        return new ExpenditureResponse(
                expenditure.getId(),
                expenditure.getName(),
                expenditure.getCategory(),
                expenditure.getAmount(),
                expenditure.getExpenditureDate(),
                expenditure.getPaymentMethod(),
                expenditure.getVendor(),
                expenditure.getReceiptPath(),
                expenditure.getNotes()
        );
    }

    public static List<ExpenditureResponse> toResponses(List<Expenditure> expenditures) {
        List<ExpenditureResponse> expenditureResponses = new ArrayList<>();

        for (Expenditure expenditure : expenditures) {
            ExpenditureResponse response = new ExpenditureResponse(
                    expenditure.getId(),
                    expenditure.getName(),
                    expenditure.getCategory(),
                    expenditure.getAmount(),
                    expenditure.getExpenditureDate(),
                    expenditure.getPaymentMethod(),
                    expenditure.getVendor(),
                    expenditure.getReceiptPath(),
                    expenditure.getNotes()
            );

            expenditureResponses.add(response);
        }

        return expenditureResponses;
    }
}
