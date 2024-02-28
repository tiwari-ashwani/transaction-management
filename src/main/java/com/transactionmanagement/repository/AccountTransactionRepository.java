package com.transactionmanagement.repository;


import org.springframework.data.repository.CrudRepository;

import com.transactionmanagement.model.entity.AccountTransaction;


public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, Long> {

}
