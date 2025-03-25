package com.hdbank.customer_service.shared.mapper;

import com.hdbank.customer_service.dto.request.AccountRequest;
import com.hdbank.customer_service.dto.response.AccountResponse;
import com.hdbank.customer_service.persistence.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountRequest request);
    AccountResponse toResponse(Account account);
}
