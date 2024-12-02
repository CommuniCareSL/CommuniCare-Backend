package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;


    @Column(name = "service_name")
    private String serviceName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}