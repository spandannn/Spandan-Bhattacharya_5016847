package com.example.employeemanagementsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e")
    List<EmployeeNameProjection> findAllEmployeeNames();
    @Query("SELECT new com.example.employeemanagementsystem.EmployeeDTO(e.name, e.email) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTOs();
    @Query("SELECT e FROM Employee e")
    List<EmployeeFullNameProjection> findAllEmployeeFullNames();

    void clear();
}
