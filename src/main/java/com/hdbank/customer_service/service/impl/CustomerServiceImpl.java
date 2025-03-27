package com.hdbank.customer_service.service.impl;

import com.hdbank.customer_service.dto.request.CustomerRequest;
import com.hdbank.customer_service.dto.response.CustomerResponse;
import com.hdbank.customer_service.persistence.entity.Customer;
import com.hdbank.customer_service.persistence.repository.CustomerRepository;
import com.hdbank.customer_service.service.CustomerService;
import com.hdbank.customer_service.shared.enumeration.ResponseEnum;
import com.hdbank.customer_service.shared.exception.BadRequestException;
import com.hdbank.customer_service.shared.exception.ResourceNotFoundException;
import com.hdbank.customer_service.shared.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        try {
            Customer customer = customerMapper.toEntity(request);
            return customerMapper.toResponse(customerRepository.save(customer));
        }
        catch (Exception e){
            throw new BadRequestException(e.getMessage(), ResponseEnum.OBJECT_EXISTS);
        }
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found", ResponseEnum.RESOURCE_NOT_FOUND));
    }

    @Override
    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found", ResponseEnum.RESOURCE_NOT_FOUND));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());

        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }
}
