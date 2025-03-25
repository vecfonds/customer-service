package com.hdbank.customer_service.controller;

import com.hdbank.customer_service.dto.request.AccountRequest;
import com.hdbank.customer_service.dto.response.BaseResponse;
import com.hdbank.customer_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(accountService.createAccount(request)));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getAccountsByCustomerId(@PathVariable UUID customerId) {
        return ResponseEntity.ok(new BaseResponse<>(accountService.getAccountsByCustomerId(customerId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(new BaseResponse<>(accountService.getAccountById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable UUID id, @RequestBody AccountRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(accountService.updateAccount(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}

