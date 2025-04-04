package com.hdbank.customer_service.service.impl;

import com.hdbank.customer_service.dto.response.TransactionResponse;
import com.hdbank.customer_service.persistence.entity.Account;
import com.hdbank.customer_service.persistence.entity.Transaction;
import com.hdbank.customer_service.persistence.repository.AccountRepository;
import com.hdbank.customer_service.persistence.repository.TransactionRepository;
import com.hdbank.customer_service.service.ExchangeRateService;
import com.hdbank.customer_service.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ExchangeRateService exchangeRateService;

    public TransactionServiceImpl(AccountRepository accountRepository,
                                  TransactionRepository transactionRepository,
                                  ExchangeRateService exchangeRateService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    @Transactional
    public TransactionResponse transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Số tiền chuyển phải lớn hơn 0");
        }

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Tài khoản gửi không tồn tại"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Tài khoản nhận không tồn tại"));

        BigDecimal exchangeRate = BigDecimal.ONE;

        // Nếu khác loại tiền tệ thì quy đổi
        if (!fromAccount.getCurrencyCode().equals(toAccount.getCurrencyCode())) {
           exchangeRate = exchangeRateService.getRate(fromAccount.getCurrencyCode(), toAccount.getCurrencyCode());
        }


        // Kiểm tra số dư
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Số dư không đủ để thực hiện giao dịch.");
        }

        // Trừ tiền từ tài khoản gửi
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        BigDecimal convertedAmount = amount.multiply(exchangeRate);
        BigDecimal oldBalance = toAccount.getBalance();
        toAccount.setBalance(oldBalance.add(convertedAmount));

        // Cộng tiền vô tài khoản nhận
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Tạo giao dịch
        Transaction transaction = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(amount)
                .currencyCode(fromAccount.getCurrencyCode())
                .build();

        transactionRepository.save(transaction);

        // Tạo response
        return new TransactionResponse(
                fromAccount.getId().toString(),
                amount,
                toAccount.getId().toString(),
                oldBalance,
                toAccount.getBalance()

        );
    }
}
