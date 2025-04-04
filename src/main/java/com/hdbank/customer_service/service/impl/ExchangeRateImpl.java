package com.hdbank.customer_service.service.impl;

import com.hdbank.customer_service.persistence.entity.ExchangeRate;
import com.hdbank.customer_service.persistence.repository.ExchangeRateRepository;
import com.hdbank.customer_service.service.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class ExchangeRateImpl implements ExchangeRateService {

    private ExchangeRateRepository exchangeRateRepository;

    public void ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }



    @Override
    public BigDecimal getRate(String fromCurrency, String toCurrency) {
        return exchangeRateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency)
                .map(ExchangeRate::getRate)
                .orElseThrow(() -> new RuntimeException("Exchange rate not found for: " + fromCurrency + " to " + toCurrency));
    }
}
