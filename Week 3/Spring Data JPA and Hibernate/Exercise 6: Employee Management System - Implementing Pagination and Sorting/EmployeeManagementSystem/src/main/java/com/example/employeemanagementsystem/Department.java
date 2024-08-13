package com.example.employeemanagementsystem;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
@NamedQuery(name = "Department.findByName",
        query = "SELECT d FROM Department d WHERE d.name = :name")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    // Getters and Setters
}
