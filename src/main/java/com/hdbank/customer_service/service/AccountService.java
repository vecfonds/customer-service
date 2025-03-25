package com.hdbank.customer_service.service;

import com.hdbank.customer_service.dto.request.AccountRequest;
import com.hdbank.customer_service.dto.response.AccountResponse;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponse createAccount(AccountRequest request);
    List<AccountResponse> getAccountsByCustomerId(UUID customerId);
    AccountResponse getAccountById(UUID id);
    AccountResponse updateAccount(UUID id, AccountRequest request);
    void deleteAccount(UUID id);
}
