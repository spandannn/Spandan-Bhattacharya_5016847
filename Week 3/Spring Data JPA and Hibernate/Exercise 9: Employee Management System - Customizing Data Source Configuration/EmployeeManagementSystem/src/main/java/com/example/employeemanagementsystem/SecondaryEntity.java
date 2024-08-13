package com.example.employeemanagementsystem;
import jakarta.persistence.*;
@Entity
@Table(name = "secondary_table")
public class SecondaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // other fields, getters, setters, etc.
}
