package com.example.accountservice.entities;

import com.example.accountservice.Model.Customer;
import com.example.accountservice.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Builder
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private LocalDate createAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;
}
