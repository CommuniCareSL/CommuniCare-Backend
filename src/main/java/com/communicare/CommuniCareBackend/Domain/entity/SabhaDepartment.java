package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SabhaDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sabha_departmentId;


    @ManyToOne
    @JoinColumn(name = "sabha_id", nullable = false)
    private Sabha sabha;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
