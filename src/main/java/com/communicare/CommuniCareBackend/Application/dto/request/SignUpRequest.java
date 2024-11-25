package com.communicare.CommuniCareBackend.Application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//Mobile App

@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String idNumber;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String district;

    @NotBlank
    private String pradeshiyaSabaha;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
