package com.example.employeemanagementsystem;
import com.example.employeemanagementsystem.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}

