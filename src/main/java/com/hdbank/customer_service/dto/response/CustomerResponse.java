package com.hdbank.customer_service.dto.response;

import com.hdbank.customer_service.shared.enumeration.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CustomerResponse {
    private UUID id;
    private String name;
    private String phone;
    private LocalDate dateOfBirth;
    private GenderEnum gender;
    private String cccd;
    private String address;
    private String email;
}
