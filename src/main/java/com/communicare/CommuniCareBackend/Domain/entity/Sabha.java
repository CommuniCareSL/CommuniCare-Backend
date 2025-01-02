package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sabha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sabhaId;

    @Column(name = "sabha_name")
    private String sabhaName;

    @Column(name = "district")
    private String district;

    @Column(name = "address")
    private String address;

    @Column( name= "sabha_mail",unique = true, nullable = false)
    private String sabhaMail;

    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Contact number must be valid (7-15 digits, optional '+')")
    private String contactNo;

}

