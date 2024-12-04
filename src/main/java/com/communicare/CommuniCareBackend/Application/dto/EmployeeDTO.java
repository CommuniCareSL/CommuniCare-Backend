package com.communicare.CommuniCareBackend.Application.dto;

public class EmployeeDTO {

    private Integer employeeId;
    private String email;
    private String address;
    private String nic;
    private String district;
    private String name;
    private String role;
    private Integer sabhaDepartmentId;  // Add this field for the department ID

    // Add constructor, getters, and setters

    public EmployeeDTO(Integer employeeId, String email, String address, String nic, String district, String name, String role, Integer sabhaDepartmentId) {
        this.employeeId = employeeId;
        this.email = email;
        this.address = address;
        this.nic = nic;
        this.district = district;
        this.name = name;
        this.role = role;
        this.sabhaDepartmentId = sabhaDepartmentId;
    }

    // Getters and Setters
}
