package com.transactionmanagement.model.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record FundTransferRequest(String payer, @NotBlank String payee, BigDecimal amount) {
}
