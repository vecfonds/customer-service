package com.hdbank.customer_service.persistence.repository;

import com.hdbank.customer_service.persistence.entity.ExchangeRate;

import java.util.Optional;

public interface ExchangeRateRepository {
    Optional<ExchangeRate> findByFromCurrencyAndToCurrency(String from, String to);

}
