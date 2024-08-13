package com.example.employeemanagementsystem;

import jakarta.persistence.*;

@Entity
@Table(name = "primary_table")
public class PrimaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // other fields, getters, setters, etc.
}
