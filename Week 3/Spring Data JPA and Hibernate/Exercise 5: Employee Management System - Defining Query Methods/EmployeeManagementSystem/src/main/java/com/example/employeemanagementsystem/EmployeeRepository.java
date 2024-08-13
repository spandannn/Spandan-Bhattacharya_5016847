package com.example.employeemanagementsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(@Param("name") String name);

    List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);
}
