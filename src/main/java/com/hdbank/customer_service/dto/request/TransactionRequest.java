package com.hdbank.customer_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransactionRequest {
    private UUID id;

    private UUID fromAccount;

    private UUID  toAccount;

    private BigDecimal amount;

    private String currencyCode;

    private BigDecimal exchange_rate;
}
