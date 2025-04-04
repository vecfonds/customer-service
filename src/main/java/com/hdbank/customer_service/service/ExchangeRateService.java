package com.hdbank.customer_service.service;
import java.math.BigDecimal;

public interface ExchangeRateService {
    BigDecimal getRate(String fromCurrency, String toCurrency);


}
