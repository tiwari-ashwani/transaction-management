package com.transactionmanagement.model.entity;


import java.math.BigDecimal;
import java.util.Set;

import com.transactionmanagement.model.AccountStatus;
import com.transactionmanagement.model.AccountType;
import com.transactionmanagement.model.AccountStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity
@Accessors(fluent = true)
@Getter
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String accountNumber;
    
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AccountTransaction> transactions;
    

}