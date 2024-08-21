package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.hateoas.EntityModel;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public List<EntityModel<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setEmail(customerDetails.getEmail());
                    Customer updatedCustomer = customerRepository.save(customer);
                    EntityModel<Customer> resource = EntityModel.of(updatedCustomer,
                            linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel(),
                            linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
