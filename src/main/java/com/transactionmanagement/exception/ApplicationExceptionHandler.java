package com.transactionmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(AccountNotFoundException.class)
  @ResponseBody 
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String accountNotFoundHandler(AccountNotFoundException ex) {
    return ex.getMessage();
  }
  
  @ExceptionHandler(InsufficientBalanceException.class)
  @ResponseBody 
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String insufficientBalanceHandler(InsufficientBalanceException ex) {
    return ex.getMessage();
  }
  
  @ExceptionHandler(CustomerNotFoundException.class)
  @ResponseBody 
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String customerNotFoundHandler(CustomerNotFoundException ex) {
    return ex.getMessage();
  }
  
  @ExceptionHandler(FundsTransferFailureException.class)
  @ResponseBody 
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String fundsTransferFailureHandler(FundsTransferFailureException ex) {
    return ex.getMessage();
  }
  
  
}