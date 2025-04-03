package com.hdbank.customer_service.service.impl;

import com.hdbank.customer_service.dto.response.TransactionResponse;
import com.hdbank.customer_service.persistence.entity.Account;
import com.hdbank.customer_service.persistence.entity.Transaction;
import com.hdbank.customer_service.persistence.repository.AccountRepository;
import com.hdbank.customer_service.persistence.repository.TransactionRepository;
import com.hdbank.customer_service.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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

        // Kiểm tra loại tiền tệ có khớp nhau không
        // if (!fromAccount.getCurrencyCode().equals(toAccount.getCurrencyCode())) {
        //     throw new CurrencyMismatchException("Không thể chuyển tiền giữa các tài khoản với loại tiền tệ khác nhau");
        //  }


        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Số dư không đủ để thực hiện giao dịch.");
        }

        // Trừ tiền từ tài khoản gửi
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        BigDecimal oldBalance = toAccount.getBalance();

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
