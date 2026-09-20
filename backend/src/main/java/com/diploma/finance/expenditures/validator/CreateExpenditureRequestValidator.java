package com.diploma.finance.expenditures.validator;

import com.diploma.finance.utilities.exception.entity.request.InvalidRequestException;
import com.diploma.finance.expenditures.dto.request.CreateExpenditureRequest;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class CreateExpenditureRequestValidator {
    private CreateExpenditureRequestValidator() {};
    public static void validate(CreateExpenditureRequest request) {
        if (request == null) {
            throw new InvalidRequestException(
                    "Expenditure request cannot be null"
            );
        }

        if (request.getUserId() == null) {
            throw new InvalidRequestException(
                    "Expenditure should have an owner/user"
            );
        }

        if (request.getName() == null || request.getName().isBlank()) {
            throw new InvalidRequestException(
                    "Expenditure should have a name"
            );
        }

        if (request.getAmount() == null) {
            throw new InvalidRequestException(
                    "Expenditure amount cannot be null"
            );
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidRequestException(
                    "Expenditure amount must be greater than zero"
            );
        }

        if (request.getCategory() == null) {
            throw new InvalidRequestException(
                    "Expenditure should have a category"
            );
        }

        if (request.getExpenditureDate() == null) {
            throw new InvalidRequestException(
                    "Expenditure should have an expenditure date"
            );
        }

        if (request.getPaymentMethod() == null) {
            throw new InvalidRequestException(
                    "Expenditure should have a payment method"
            );
        }

        if (request.getVendor() == null) {
            throw new InvalidRequestException(
                    "Expenditure should have a vendor"
            );
        }

        if (request.getNotes() != null && request.getNotes().length() > 100) {
            throw new InvalidRequestException(
                    "Receipt note cannot exceed 100 characters"
            );
        }
    }

    public static void validateReceipt(MultipartFile receipt) {
        if (receipt == null || receipt.isEmpty()) {
            return;
        }

        String contentType = receipt.getContentType();

        if (contentType == null || !contentType.startsWith("image/")) {

            throw new InvalidRequestException(
                    "Receipt must be an image"
            );
        }

        if (receipt.getSize() > 5 * 1024 * 1024) {
            throw new InvalidRequestException(
                    "Receipt cannot exceed 5 MB"
            );
        }
    }
}
