package com.example.employeemanagementsystem;
import com.example.EmployeeManagementSystem.Employee;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Define derived query methods here
}
