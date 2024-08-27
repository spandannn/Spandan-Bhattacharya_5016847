package com.book.bookstoreapi.controller;

import com.book.bookstoreapi.dto.CustomerDTO;
import com.book.bookstoreapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(addLinks(createdCustomer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id,
                                                       @RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, acceptHeader)
                .body(addLinks(customerDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(
            @RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader) {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, acceptHeader)
                .body(customers.stream().map(this::addLinks).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return new ResponseEntity<>(addLinks(updatedCustomer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CustomerDTO addLinks(CustomerDTO customerDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customerDTO.getId(), HttpHeaders.CONTENT_TYPE)).withSelfRel();
        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers(HttpHeaders.CONTENT_TYPE)).withRel("all-customers");

        customerDTO.add(selfLink);
        customerDTO.add(allCustomersLink);

        return customerDTO;
    }
}
