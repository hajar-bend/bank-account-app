package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lombok.Builder;
import java.util.List;

@Builder
@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            List<Customer> customerList;
            customerList = List.of(
                    Customer.builder()
                            .firstName("Hassan")
                            .lastName("Bendaouia")
                            .email("bend@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("Mohamed")
                            .lastName("Bend")
                            .email("mbend@gmail.com")
                            .build()

            );
            customerRepository.saveAll(customerList);
        };
    }

}
