package com.hdbank.customer_service.persistence.entity;

import com.hdbank.customer_service.shared.enumeration.AccountTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private AccountTypeEnum accountType;

    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false)
    private BigDecimal balance;
}
