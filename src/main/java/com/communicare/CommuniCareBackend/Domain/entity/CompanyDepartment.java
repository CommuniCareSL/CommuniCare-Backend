package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CompanyDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyDepartmentId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "complaint_handler_id", nullable = false)
    private Employee employee;
}
