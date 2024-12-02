package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @Column(name = "reservation_name")
    private String reservationName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}

