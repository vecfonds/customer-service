package com.hdbank.customer_service.controller;

import com.hdbank.customer_service.dto.request.CustomerRequest;
import com.hdbank.customer_service.dto.response.BaseResponse;
import com.hdbank.customer_service.dto.response.CustomerResponse;
import com.hdbank.customer_service.service.CustomerService;
import com.hdbank.customer_service.utils.LogUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    private final LogUtil logUtil;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest request, HttpServletRequest http) {
        CustomerResponse response = customerService.createCustomer(request);
        logUtil.logHelper(http, request, response);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(new BaseResponse<>(customerService.getAllCustomers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable UUID id) {
        return ResponseEntity.ok(new BaseResponse<>(customerService.getCustomerById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable UUID id, @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(customerService.updateCustomer(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(new BaseResponse<>());
    }
}
