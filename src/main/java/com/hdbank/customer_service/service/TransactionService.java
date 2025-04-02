package com.hdbank.customer_service.service;

import com.hdbank.customer_service.dto.request.TransactionRequest;
import com.hdbank.customer_service.dto.response.TransactionResponse;
import com.hdbank.customer_service.persistence.entity.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionService {
    //@Transactional
    TransactionResponse transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount);

}
