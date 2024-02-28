package com.transactionmanagement.model.dto;


import java.math.BigDecimal;

import com.transactionmanagement.model.AccountStatus;
import com.transactionmanagement.model.AccountType;
import com.transactionmanagement.model.AccountStatus;

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


@Data
public class AccountDto {
    
    private Long id;
    
    private AccountType type;

    private AccountStatus status;

    private BigDecimal availableBalance;


}