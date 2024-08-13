package com.example.employeemanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void saveAllEmployees(List<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            employeeRepository.save(employees.get(i));
            if (i % 20 == 0) { // Flush and clear every 20 entities
                employeeRepository.flush();
                employeeRepository.clear();
            }
        }
    }
}
