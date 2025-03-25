package com.hdbank.customer_service.shared.enumeration;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    SUCCESS("0000", "Success", ""),
    BAD_REQUEST("0400", "Bad request", "Invalid request"),
    INTERNAL_ERROR("0500", "Unknown error", "Unknown error"),
    RESOURCE_NOT_FOUND("0404", "Resource not found", "Resource not found"),
    DATA_SUCCESS("2930", "Success", "Getting data success."),
    DATA_NOT_FOUND("2931", "Decline", "No information found"),
    CL_CCY_IS_REQUIRED("0400", "Bad request", "Bad request"),
    OBJECT_EXISTS("0409", "Object exists with the given information", "Object exists with the given information");

    private final String responseCode;
    private final String message;
    private final String description;

    private ResponseEnum(final String responseCode, final String message, final String description) {
        this.responseCode = responseCode;
        this.message = message;
        this.description = description;
    }
}
