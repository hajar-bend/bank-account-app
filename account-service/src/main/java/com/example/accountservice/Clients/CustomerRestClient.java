package com.example.accountservice.Clients;

import com.example.accountservice.Model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "CustomerService", fallbackMethod = "getDefaultCustomer")
     Customer findCustomerById(@PathVariable Long id);
    @GetMapping("/customers")
    @CircuitBreaker(name = "CustomerService", fallbackMethod = "getDefaultCustomers")
     List<Customer> allCustomers();


    default  Customer getDefaultCustomer(Long id, Exception exception) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Source not available");
        customer.setLastName("Source Not Available");
        customer.setEmail("Not Available");
        return customer;
    }

    default  List<Customer> getDefaultCustomers(Exception exception) {
        return List.of();
    }
}
