package com.transactionmanagement.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionmanagement.model.request.FundTransferRequest;
import com.transactionmanagement.response.FundTransferResponse;
import com.transactionmanagement.service.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/{customerId}/transaction")
public class TransactionController {

	 private final TransactionService transactionService;

	 @PostMapping("/fund-transfer")
	 public FundTransferResponse fundTransfer(@PathVariable(required = true) Long customerId,@Valid @RequestBody FundTransferRequest fundTransferRequest) {

	        log.info("Fund transfer initiated from {}", fundTransferRequest.payee());
	        return transactionService.fundTransfer(customerId,fundTransferRequest);

	 }

  
    

}
