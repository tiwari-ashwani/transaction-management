package com.transactionmanagement.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.transactionmanagement.exception.AccountNotFoundException;
import com.transactionmanagement.exception.CustomerNotFoundException;
import com.transactionmanagement.exception.FundsTransferFailureException;
import com.transactionmanagement.exception.InsufficientBalanceException;
import com.transactionmanagement.model.TransactionStatus;
import com.transactionmanagement.model.TransactionType;
import com.transactionmanagement.model.entity.Account;
import com.transactionmanagement.model.entity.AccountTransaction;
import com.transactionmanagement.model.request.FundTransferRequest;
import com.transactionmanagement.repository.AccountRepository;
import com.transactionmanagement.repository.AccountTransactionRepository;
import com.transactionmanagement.response.FundTransferResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TransactionService {
    
	private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final AccountTransactionRepository transactionRepository;

    public FundTransferResponse fundTransfer(Long customerId,FundTransferRequest fundTransferRequest) {
    	
    	accountService.validateRequest(customerId, fundTransferRequest.payer());
    	
    	Account sourceAccount = accountRepository.findByAccountNumber(fundTransferRequest.payer()).get();
    	Account targetAccount = accountRepository.findByAccountNumber(fundTransferRequest.payee()).get();

       
        validateBalance(sourceAccount,targetAccount,fundTransferRequest.amount());

        UUID transactionId = transfer(sourceAccount, targetAccount, fundTransferRequest.amount());
        return new FundTransferResponse("Transaction successfully completed",transactionId);
       

    }

    
    private void validateBalance(Account source,Account target, BigDecimal amount) {    		
		log.info("Validating balance : {}", amount);

        if (source.balance().compareTo(BigDecimal.ZERO) < 0 || source.balance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException("Insufficient balance!!");
        }
    }

    public UUID transfer(Account sourceAccount, Account targetAccount, BigDecimal amount) {
        UUID transactionId = UUID.randomUUID();

    	try {
    		log.info("Initiating fund transfer between {} {} : ", sourceAccount,targetAccount);

	        transactionRepository.save(AccountTransaction.builder()
	    		    .type(TransactionType.DEBIT)
	    		    .status(TransactionStatus.INPROGRESS)
	    		    .id(transactionId)
	    		    .account(sourceAccount)
	    		    .amount(amount.negate())
	    		    .build());
	        
	        sourceAccount.toBuilder().balance(sourceAccount.balance().subtract(amount)).build();
		    accountRepository.save(sourceAccount);
		    
    		log.info("Source Account updated {}: ", sourceAccount.balance());

		    
		    targetAccount.toBuilder().balance(targetAccount.balance().add(amount)).build();
     	    accountRepository.save(targetAccount);
	
    		log.info("Target Account updated {}: ", targetAccount.balance());

		    transactionRepository.save(AccountTransaction.builder()
		    		    .type(TransactionType.DEBIT)
		    		    .status(TransactionStatus.DONE)
		    		    .id(transactionId)
		    		    .account(sourceAccount)
		    		    .amount(amount.negate())
		    		    .build());
		    
    		log.info("Transaction table updated : ");

	
	        return transactionId;
        }catch(Exception ex) {
        	transactionRepository.save(AccountTransaction.builder()
	    		    .type(TransactionType.DEBIT)
	    		    .status(TransactionStatus.FAILED)
	    		    .id(transactionId)
	    		    .account(sourceAccount)
	    		    .amount(amount.negate())
	    		    .build());
        	throw new FundsTransferFailureException("Something went wrong,unable to transfer funds!!");

        }
        
        }


}
