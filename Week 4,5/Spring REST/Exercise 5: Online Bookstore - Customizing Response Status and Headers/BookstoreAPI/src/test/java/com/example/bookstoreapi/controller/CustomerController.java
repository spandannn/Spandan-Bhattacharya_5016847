package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestParam String name,
                                                     @RequestParam String email,
                                                     @RequestParam String password) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }
}
