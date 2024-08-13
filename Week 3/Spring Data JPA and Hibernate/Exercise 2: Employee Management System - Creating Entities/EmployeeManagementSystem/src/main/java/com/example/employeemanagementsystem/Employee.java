package com.example.EmployeeManagementSystem;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private com.example.EmployeeManagementSystem.Department department;

    // Getters and Setters
}
