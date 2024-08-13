package com.example.employeemanagementsystem;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeFullNameProjection {
    @Value("#{target.name + ' ' + target.email}")
    String getFullName();
}