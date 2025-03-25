package com.hdbank.customer_service.service.impl;

import com.hdbank.customer_service.dto.request.AccountRequest;
import com.hdbank.customer_service.dto.response.AccountResponse;
import com.hdbank.customer_service.persistence.entity.Account;
import com.hdbank.customer_service.persistence.entity.Customer;
import com.hdbank.customer_service.persistence.repository.AccountRepository;
import com.hdbank.customer_service.persistence.repository.CustomerRepository;
import com.hdbank.customer_service.service.AccountService;
import com.hdbank.customer_service.shared.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createAccount(AccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Account account = accountMapper.toEntity(request);
        account.setCustomer(customer);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountResponse> getAccountsByCustomerId(UUID customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponse getAccountById(UUID id) {
        return accountRepository.findById(id)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public AccountResponse updateAccount(UUID id, AccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setCurrencyCode(request.getCurrencyCode());
        account.setBalance(request.getBalance());

        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }
}
