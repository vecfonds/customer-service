package com.hdbank.customer_service.controller;

import com.hdbank.customer_service.dto.request.TransactionRequest;
import com.hdbank.customer_service.dto.response.TransactionResponse;
import com.hdbank.customer_service.persistence.entity.Transaction;
import com.hdbank.customer_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public TransactionResponse transfer(
            @RequestParam UUID fromAccountId,
            @RequestParam UUID toAccountId,
            @RequestParam BigDecimal amount
    ) {
        return transactionService.transfer(fromAccountId, toAccountId, amount);
    }
}
