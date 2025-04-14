package com.hdbank.customer_service.dto.enums;

import lombok.Getter;

@Getter
public enum CustomerLogType {
    CUSTOMER("CUSTOMER"),
    ACCOUNT("ACCOUNT");

    private final String value;

    CustomerLogType(String value) {
        this.value = value;
    }
}
