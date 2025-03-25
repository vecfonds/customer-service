package com.hdbank.customer_service.shared.mapper;

import com.hdbank.customer_service.dto.request.CustomerRequest;
import com.hdbank.customer_service.dto.response.CustomerResponse;
import com.hdbank.customer_service.persistence.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerResponse toResponse(Customer customer);
}