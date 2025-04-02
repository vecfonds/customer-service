package com.hdbank.customer_service.dto.request;

import com.hdbank.customer_service.shared.enumeration.AccountTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AccountRequest {
    private UUID customerId;
    private AccountTypeEnum accountType;
    private String currencyCode;
    private BigDecimal balance;
}
