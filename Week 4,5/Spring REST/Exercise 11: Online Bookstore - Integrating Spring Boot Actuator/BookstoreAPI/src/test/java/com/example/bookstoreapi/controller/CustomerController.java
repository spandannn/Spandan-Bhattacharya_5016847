package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Customer;
import com.example.bookstoreapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EntityModel<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream()
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers")))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<Customer>> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<EntityModel<Customer>> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(id, customerDetails)
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
