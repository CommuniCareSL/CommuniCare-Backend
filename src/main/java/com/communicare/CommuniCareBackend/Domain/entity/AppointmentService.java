package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AppointmentService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // Bidirectional Many-to-Many relationship with Appointment
    @ManyToMany(mappedBy = "appointmentServices")
    private List<Appointment> appointments; // A collection to hold related appointments
}
