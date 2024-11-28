package com.communicare.CommuniCareBackend.Domain.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
