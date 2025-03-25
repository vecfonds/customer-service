package com.hdbank.customer_service.shared.exception;

import com.hdbank.customer_service.shared.enumeration.ResponseEnum;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message, ResponseEnum.BAD_REQUEST);
    }

    public BadRequestException(String message, ResponseEnum errorInfo) {
        super(message, errorInfo);
    }

    public BadRequestException(ResponseEnum errorInfo) {
        super(errorInfo);
    }

}
