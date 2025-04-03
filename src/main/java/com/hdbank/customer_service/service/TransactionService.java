package com.hdbank.customer_service.service;

import com.hdbank.customer_service.dto.response.TransactionResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionService {
    TransactionResponse transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount);

}
