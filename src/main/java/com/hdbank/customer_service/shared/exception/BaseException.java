package com.hdbank.customer_service.shared.exception;

import com.hdbank.customer_service.shared.enumeration.ResponseEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    protected final ResponseEnum errorInfo;

    public BaseException(String message) {
        super(message);
        errorInfo = ResponseEnum.INTERNAL_ERROR;
    }

    public BaseException(String message, ResponseEnum errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public BaseException(ResponseEnum errorInfo) {
        super(errorInfo.getMessage());
        this.errorInfo = errorInfo;
    }
}
