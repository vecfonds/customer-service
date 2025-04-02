package com.hdbank.customer_service.dto.request;

import com.hdbank.customer_service.persistence.entity.Account;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.validator.constraints.UUID;
import java.util.UUID;
import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    private UUID id;

    private UUID fromAccount;

    private UUID  toAccount;

    private BigDecimal amount;

    private String currencyCode;

    private BigDecimal exchange_rate;

    public UUID getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = UUID.fromString(fromAccount);
    }

    public UUID getToAccount() {
        return toAccount;
    }


    public void setToAccount(String toAccount) {
        this.toAccount = UUID.fromString(toAccount);
    }
}
