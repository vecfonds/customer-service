package com.hdbank.customer_service.dto.request;

import com.hdbank.customer_service.shared.enumeration.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String phone;
    private LocalDate dateOfBirth;
    private GenderEnum gender;
    private String cccd;
    private String address;
    private String email;
}
