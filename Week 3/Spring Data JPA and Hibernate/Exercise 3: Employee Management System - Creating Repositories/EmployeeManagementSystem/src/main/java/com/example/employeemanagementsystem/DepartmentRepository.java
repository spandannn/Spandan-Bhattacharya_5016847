package com.example.employeemanagementsystem;
import com.example.EmployeeManagementSystem.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}

