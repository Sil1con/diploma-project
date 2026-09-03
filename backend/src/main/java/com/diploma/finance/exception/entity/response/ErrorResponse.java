package com.diploma.finance.exception.entity.response;

public record ErrorResponse(
    int status,
    String message
) {
}
