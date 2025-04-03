package com.hdbank.customer_service.service;

import com.hdbank.customer_service.dto.response.TransactionResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public interface TransactionService {
    TransactionResponse transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount);

}
