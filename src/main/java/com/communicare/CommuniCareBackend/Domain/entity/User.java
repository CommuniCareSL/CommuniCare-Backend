package com.communicare.CommuniCareBackend.Domain.entity;
//Mobile App
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Mobile App

@Entity
@Table(name = "users")
@Getter // Lombok generates getters for all fields
@Setter // Lombok generates setters for all fields
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Primary key

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "district")
    private String district;

    @Column(name = "pradeshiya_sabaha")
    private String pradeshiyaSabaha;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    // Constructor and other methods can be added if needed
}
