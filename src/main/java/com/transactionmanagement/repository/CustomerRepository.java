package com.transactionmanagement.repository;



import org.springframework.data.repository.CrudRepository;

import com.transactionmanagement.model.entity.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {


}
