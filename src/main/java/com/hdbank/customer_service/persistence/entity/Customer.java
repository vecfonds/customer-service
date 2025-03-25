package com.hdbank.customer_service.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @NotBlank(message = "Tên không được để trống")
    @Column(nullable = false)
    private String name;

//    @NotBlank(message = "Email không được để trống")
//    @Email(message = "Email không hợp lệ")
    @Column(unique = true, nullable = false)
    private String email;

//    @NotBlank(message = "Số điện thoại không được để trống")
//    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại không hợp lệ")
    @Column(unique = true, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();
}

