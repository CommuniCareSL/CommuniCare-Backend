package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Sabha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sabhaId;

    @Column(name = "sabha_name")
    private String sabhaName;

    @Column(name = "district")
    private String district;

    @Column(name = "address")
    private String address;

    @Email(message = "Invalid email format")
    @Column( name= "sabha_mail",unique = true, nullable = false)
    private String sabhaMail;

    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Contact number must be valid (7-15 digits, optional '+')")
    private String contactNo;

    @OneToMany(mappedBy = "sabha")
    private List<SabhaDepartment> sabhaDepartments;

}

