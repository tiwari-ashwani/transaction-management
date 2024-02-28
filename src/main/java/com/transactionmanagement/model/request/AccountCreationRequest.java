package com.transactionmanagement.model.request;

import java.math.BigDecimal;

import com.transactionmanagement.model.AccountType;

public record AccountCreationRequest(AccountType type, BigDecimal balance) {
	
}
