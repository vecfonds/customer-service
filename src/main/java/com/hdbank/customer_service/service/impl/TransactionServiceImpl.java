package com.hdbank.customer_service.service.impl;

import com.hdbank.customer_service.dto.request.TransactionRequest;
import com.hdbank.customer_service.dto.response.TransactionResponse;
import com.hdbank.customer_service.persistence.entity.Account;
import com.hdbank.customer_service.persistence.entity.Transaction;
import com.hdbank.customer_service.persistence.repository.AccountRepository;
import com.hdbank.customer_service.persistence.repository.TransactionRepository;
import com.hdbank.customer_service.service.TransactionService;
import jakarta.transaction.Transactional;
//import org.hibernate.validator.constraints.UUID;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
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

/*
@Override
    @Transactional
    public void transferMoney(TransactionRequest request) {
        UUID fromAccountId = request.getFromAccount();
        UUID toAccountId = request.getToAccount();

       Account fromAccount = accountRepository.findById(request.getFromAccount().getId())
                .orElseThrow(() -> new RuntimeException("Tài khoản gửi không tồn tại"));
        Account toAccount = accountRepository.findById(request.getToAccount().getId())
                .orElseThrow(() -> new RuntimeException("Tài khoản nhận không tồn tại"));
Account fromAccount = accountRepository.findById(fromAccountId)
        .orElseThrow(() -> new RuntimeException("Tài khoản gửi không tồn tại"));
Account toAccount = accountRepository.findById(toAccountId)
        .orElseThrow(() -> new RuntimeException("Tài khoản nhận không tồn tại"));

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
        throw new RuntimeException("Số dư không đủ để chuyển tiền");
        }

                fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccountId);
        transaction.setToAccount(toAccountId);
        transaction.setAmount(request.getAmount());
        transactionRepository.save(transaction);
    }
*/
    /*
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public void transferMoney(TransactionRequest request) {
        Account fromAccount = accountRepository.findById(request.getFromAccount().getId())
                .orElseThrow(() -> new RuntimeException("From account not found"));

        Account toAccount = accountRepository.findById(request.getToAccount().getId())
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Thực hiện giao dịch
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        // Lưu vào DB
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(request.getAmount());

        transactionRepository.save(transaction);
    }*/

