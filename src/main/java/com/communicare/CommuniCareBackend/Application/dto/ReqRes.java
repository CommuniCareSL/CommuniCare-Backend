package com.communicare.CommuniCareBackend.Application.dto;

import com.communicare.CommuniCareBackend.Domain.entity.Users;
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
    private String district;
    private String sabaha;
    private Integer registernumber;
    private Integer number;
    private String role;
    private String email;
    private String password;
    private Users ourUsers;
    private List<Users> ourUsersList;

}