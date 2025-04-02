package com.hdbank.customer_service.persistence.repository;

import com.hdbank.customer_service.persistence.entity.Transaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction, UUID> {

}
