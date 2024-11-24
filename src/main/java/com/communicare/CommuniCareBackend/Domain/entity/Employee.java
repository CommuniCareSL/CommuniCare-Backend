package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String sabaha;

    @Column(nullable = false, unique = true)
    private Integer registernumber;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // Constructors
    public Employee() {
    }

    public Employee(String email, String district, String sabaha, Integer registernumber, Integer number, String name, String password, String role) {
        this.email = email;
        this.district = district;
        this.sabaha = sabaha;
        this.registernumber = registernumber;
        this.number = number;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSabaha() {
        return sabaha;
    }

    public void setSabaha(String sabaha) {
        this.sabaha = sabaha;
    }

    public Integer getRegisternumber() {
        return registernumber;
    }

    public void setRegisternumber(Integer registernumber) {
        this.registernumber = registernumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
