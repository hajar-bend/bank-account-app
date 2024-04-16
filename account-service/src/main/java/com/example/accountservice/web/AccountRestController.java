package com.example.accountservice.web;

import com.example.accountservice.Clients.CustomerRestClient;
import com.example.accountservice.Model.Customer;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {

    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository accountRepository,CustomerRestClient customerRestClient) {
        this.customerRestClient= customerRestClient;
        this.accountRepository = accountRepository;
    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> accountList= accountRepository.findAll();
        accountList.forEach(acc -> {
            acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
        });
        return accountList;
    }
    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount = accountRepository.findById(id).get();
        Customer customer= customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
