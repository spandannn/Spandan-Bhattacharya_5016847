package com.book.bookstoreapi.service;

import com.book.bookstoreapi.controller.CustomerController;
import com.book.bookstoreapi.dto.CustomerDTO;
import com.book.bookstoreapi.model.Customer;
import com.book.bookstoreapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return addLinks(mapToDTO(savedCustomer));
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::mapToDTO)
                .map(this::addLinks)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::mapToDTO)
                .map(this::addLinks)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customerDTO.getName());
                    existingCustomer.setEmail(customerDTO.getEmail());
                    existingCustomer.setAddress(customerDTO.getAddress());
                    Customer updatedCustomer = customerRepository.save(existingCustomer);
                    return addLinks(mapToDTO(updatedCustomer));
                }).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }

    private Customer mapToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        return customer;
    }

    private CustomerDTO addLinks(CustomerDTO customerDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customerDTO.getId(), HttpHeaders.CONTENT_TYPE)).withSelfRel();
        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers(HttpHeaders.CONTENT_TYPE)).withRel("all-customers");

        customerDTO.add(selfLink);
        customerDTO.add(allCustomersLink);

        return customerDTO;
    }
}
