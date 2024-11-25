package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "company_department_id", nullable = false)
    private CompanyDepartment companyDepartment;
}
