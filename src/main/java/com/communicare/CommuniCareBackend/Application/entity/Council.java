package com.communicare.CommuniCareBackend.Application.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "councils")
public class Council {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String councilName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String telephoneNo;

    @Column(nullable = false)
    private String email;
}

