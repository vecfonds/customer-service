package com.hdbank.customer_service.shared.exception;

import com.hdbank.customer_service.shared.enumeration.ResponseEnum;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {
        super(message, ResponseEnum.RESOURCE_NOT_FOUND);
    }

    public ResourceNotFoundException(String message, ResponseEnum errorInfo) {
        super(message, errorInfo);
    }

    public ResourceNotFoundException(ResponseEnum errorInfo) {
        super(errorInfo);
    }
}
