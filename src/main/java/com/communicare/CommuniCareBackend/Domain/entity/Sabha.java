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
    private Long sabhaId;

    @Column(name = "sabha_name")
    private String sabha_name;

    @Column(name = "sabha_district")
    private String sabha_district;

    @Column(name = "address")
    private String address;

    @Email(message = "Invalid email format")
    @Column( name= "sabha_mail",unique = true, nullable = false)
    private String sabha_mail;

    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Contact number must be valid (7-15 digits, optional '+')")
    private String contact_no;

}

