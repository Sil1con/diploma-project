package com.diploma.finance.expenditures.mapper;

import com.diploma.finance.expenditures.dto.request.CreateExpenditureRequest;
import com.diploma.finance.expenditures.entity.Expenditure;
import com.diploma.finance.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public class ExpenditureRequestMapper {
    public static Expenditure toExpenditure(CreateExpenditureRequest request, User user) {

        return new Expenditure(
                user,
                request.getName(),
                request.getCategory(),
                request.getAmount(),
                request.getExpenditureDate(),
                request.getPaymentMethod(),
                request.getVendor(),
                null,
                request.getNotes()
        );
    }
}
