package com.diploma.finance.utilities.exception.entity.response;

public record ErrorResponse(
    int status,
    String message
) {
}
