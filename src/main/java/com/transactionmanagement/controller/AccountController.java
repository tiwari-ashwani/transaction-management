package com.transactionmanagement.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transactionmanagement.exception.AccountNotFoundException;
import com.transactionmanagement.model.request.AccountCreationRequest;
import com.transactionmanagement.response.AccountResponse;
import com.transactionmanagement.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/{customerId}/account")
public class AccountController {

	private final AccountService accountService;

	@GetMapping(path = "/{accountNumber}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAccount(@PathVariable(required = true)  Long customerId,@PathVariable(required = true) String accountNumber) throws AccountNotFoundException  {
		log.info("Fetching Customer Account by Number {} : ", accountNumber);
		var account=  accountService.getAccount(customerId,accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);

	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(CREATED)
	public ResponseEntity<?> createAccount(@PathVariable(required = true) Long customerId, @RequestBody AccountCreationRequest request) {
		log.info("Creating New Account for the Customer ID {} : ", customerId);
	        try {
			AccountResponse response = accountService.createAccount(customerId,request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } catch (DataIntegrityViolationException | IllegalArgumentException ex) {
	            log.warn("Error during customer account creation: {}", ex.getMessage());
	            return new ResponseEntity<>(request, HttpStatus.BAD_REQUEST);
	        } 
	}

}
