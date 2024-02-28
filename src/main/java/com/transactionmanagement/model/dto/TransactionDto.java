package com.transactionmanagement.model.dto;


import java.math.BigDecimal;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import com.transactionmanagement.model.TransactionType;



@Data
public class TransactionDto {
    
    private Long id; 

    private BigDecimal amount;
   
    private TransactionType type;

   

}