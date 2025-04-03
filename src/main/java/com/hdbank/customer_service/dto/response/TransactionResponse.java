package com.hdbank.customer_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponse {
    String fromAccountNumber;
    BigDecimal amount;
    String toAccountNumber;
    BigDecimal oldBalance;
    BigDecimal newBalance;
}
