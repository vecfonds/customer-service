package com.hdbank.customer_service.service.impl;

import com.hdbank.customer_service.dto.request.AccountRequest;
import com.hdbank.customer_service.dto.response.AccountResponse;
import com.hdbank.customer_service.persistence.entity.Account;
import com.hdbank.customer_service.persistence.entity.Customer;
import com.hdbank.customer_service.persistence.repository.AccountRepository;
import com.hdbank.customer_service.persistence.repository.CustomerRepository;
import com.hdbank.customer_service.service.AccountService;
import com.hdbank.customer_service.shared.enumeration.ResponseEnum;
import com.hdbank.customer_service.shared.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found!", ResponseEnum.RESOURCE_NOT_FOUND));

        Account account = accountMapper.toEntity(request);
        account.setCustomer(customer);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountResponse> getAccountsByCustomerId(UUID customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public AccountResponse getAccountById(UUID id) {
        return accountRepository.findById(id)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found", ResponseEnum.RESOURCE_NOT_FOUND));
    }

    @Override
    public AccountResponse updateAccount(UUID id, AccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found", ResponseEnum.RESOURCE_NOT_FOUND));

        account.setCurrencyCode(request.getCurrencyCode());
        account.setBalance(request.getBalance());

        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(UUID id) {
        if (!accountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Account does not exist.", ResponseEnum.RESOURCE_NOT_FOUND);
        }

        accountRepository.deleteById(id);
    }
}
