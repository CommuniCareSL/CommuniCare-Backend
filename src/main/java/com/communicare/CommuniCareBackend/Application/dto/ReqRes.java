package com.communicare.CommuniCareBackend.Application.dto;

import com.communicare.CommuniCareBackend.Domain.entity.Employees;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String address;
    private String nic;
    private String district;
    private Integer sabhaId;
    private String role;
//    private Integer sabhaDepartmentId;
    private Integer departmentId;
    private String email;
    private String password;
    private Employees ourEmployees;
    private List<Employees> ourEmployeesList;

}