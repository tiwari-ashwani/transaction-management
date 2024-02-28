package com.transactionmanagement.response;

import java.math.BigDecimal;

import com.transactionmanagement.model.AccountStatus;
import com.transactionmanagement.model.AccountType;

public record AccountResponse(Long id,String accountNumber, AccountType type, AccountStatus status,BigDecimal availableBalance,Long customerId) {

}