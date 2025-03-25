package com.hdbank.customer_service.service;

import com.hdbank.customer_service.dto.request.CustomerRequest;
import com.hdbank.customer_service.dto.response.CustomerResponse;
import com.hdbank.customer_service.persistence.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(UUID id);
    CustomerResponse updateCustomer(UUID id, CustomerRequest request);
    void deleteCustomer(UUID id);
}
