package com.example.EmployeeManagementSystem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<com.example.EmployeeManagementSystem.Employee> employees;

    // Getters and Setters
}
