package com.hdbank.customer_service.dto.response;

import com.hdbank.customer_service.shared.enumeration.AccountTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AccountResponse {
    private UUID id;
    private AccountTypeEnum accountType;
    private String currencyCode;
    private BigDecimal balance;
}
