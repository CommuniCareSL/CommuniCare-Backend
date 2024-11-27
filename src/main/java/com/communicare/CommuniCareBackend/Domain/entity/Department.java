package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_id;

    @Column(name = "department_name")
    private String department_name;
}
