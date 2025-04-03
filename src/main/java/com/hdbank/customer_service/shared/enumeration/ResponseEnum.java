package com.hdbank.customer_service.shared.enumeration;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    SUCCESS("0000", "Success", ""),
    BAD_REQUEST("0400", "Bad request", "Invalid request"),
    INTERNAL_ERROR("0500", "Unknown error", "Unknown error"),
    RESOURCE_NOT_FOUND("0404", "Resource not found", "Resource not found"),
    RESOURCE_ALREADY_EXISTS("0409", "Resource already exists.", "Resource already exists with the given information");

    private final String responseCode;
    private final String message;
    private final String description;

    ResponseEnum(final String responseCode, final String message, final String description) {
        this.responseCode = responseCode;
        this.message = message;
        this.description = description;
    }
}
