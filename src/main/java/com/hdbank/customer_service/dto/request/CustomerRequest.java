package com.hdbank.customer_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String email;
    private String phone;
}
