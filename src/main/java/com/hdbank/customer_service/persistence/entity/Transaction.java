package com.hdbank.customer_service.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="from_account",nullable = false)
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name="to_account",nullable = false)
    private Account toAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false)
    private BigDecimal exchange_rate;

    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();

}
