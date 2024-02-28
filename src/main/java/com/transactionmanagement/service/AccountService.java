package com.transactionmanagement.service;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.transactionmanagement.exception.AccountNotFoundException;
import com.transactionmanagement.exception.CustomerNotFoundException;
import com.transactionmanagement.model.AccountStatus;
import com.transactionmanagement.model.entity.Account;
import com.transactionmanagement.model.entity.Customer;
import com.transactionmanagement.model.request.AccountCreationRequest;
import com.transactionmanagement.repository.AccountRepository;
import com.transactionmanagement.repository.CustomerRepository;
import com.transactionmanagement.response.AccountResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountService {

	private static final int LENGTH = 8;

	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;

	public AccountResponse getAccount(Long customerId, String accountNumber) {

		var account = validateRequest(customerId, accountNumber);
		return accountResponse(customerId, account);

	}

	public AccountResponse createAccount(Long customerId, AccountCreationRequest request) {
		var account = customerRepository.findById(customerId)
				.map(customer -> processAccountRequest(customer, request))
				.orElseThrow(() -> new CustomerNotFoundException("Bad customerId"));

		return accountResponse(customerId, account);
	}
	
    public Account validateRequest(Long customerId, String accountNumber) {
    	
		log.info("Validating inoming request");

    	customerRepository
        .findById(customerId)
        .orElseThrow(() -> {
			log.error("Invalid customer id {}", customerId);
			throw new CustomerNotFoundException("Bad customerId");
		});	
    	
        return accountRepository.findByAccountNumber(accountNumber).stream()
		.filter(a -> a.customer().id().equals(customerId)).findAny().orElseThrow(() -> {
		log.error("Invalid account id {}", accountNumber);
	 	throw new AccountNotFoundException("Account not found");
	    });	
	}

	private AccountResponse accountResponse(Long customerId, Account account) {
		return new AccountResponse(account.id(), account.accountNumber(), account.type(), account.status(),
				account.balance(), customerId);

	}

	private Account processAccountRequest(Customer customer, AccountCreationRequest request) {
		
		return accountRepository.save(Account.builder().accountNumber(RandomStringUtils.randomAlphanumeric(LENGTH))
				.type(request.type()).status(AccountStatus.ACTIVE).balance(request.balance()).customer(customer).build());

	}
	

}
