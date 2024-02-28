package com.transactionmanagement.model.entity;


import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import com.transactionmanagement.model.TransactionStatus;
import com.transactionmanagement.model.TransactionType;

@Entity
@Accessors(fluent = true)
@Getter
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; 

    private BigDecimal amount;
   
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    

    @ManyToOne(optional=false)
    @JoinColumn(name = "account_id",nullable=false)
    private Account account;


}