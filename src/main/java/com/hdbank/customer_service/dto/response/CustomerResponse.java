package com.hdbank.customer_service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerResponse {
    private UUID id;
    private String name;
    private String email;
    private String phone;
}
