package com.transactionmanagement.response;

import java.util.UUID;

public record FundTransferResponse(String message, UUID transactionId) {

}