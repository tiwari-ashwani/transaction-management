package com.transactionmanagement.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.transactionmanagement.model.entity.Account;
import com.transactionmanagement.model.entity.Customer;



public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomer(Customer customer);


}
