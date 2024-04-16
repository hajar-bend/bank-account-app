package com.example.accountservice;

import com.example.accountservice.Clients.CustomerRestClient;
import com.example.accountservice.Model.Customer;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.enums.AccountType;
import com.example.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@EnableFeignClients
@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
        return args -> {
            List<Customer> allCustomers = customerRestClient.allCustomers();
            allCustomers.forEach(customer -> {BankAccount bankAccount1= BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .currency("MAD")
                    .balance(980000)
                    .createAt(LocalDate.now())
                    .type(AccountType.CURRENT_ACCOUNT)
                    .customerId(customer.getId())
                    .build();

                BankAccount bankAccount2= BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("MAD")
                        .balance(1230000)
                        .createAt(LocalDate.now())
                        .type(AccountType.CURRENT_ACCOUNT)
                        .customerId(customer.getId())
                        .build();

                bankAccountRepository.save(bankAccount1);
                bankAccountRepository.save(bankAccount2);

            });




        };
    }
}
