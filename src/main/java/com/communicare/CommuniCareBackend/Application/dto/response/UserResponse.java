package com.communicare.CommuniCareBackend.Application.dto.response;

//Web application user data response
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long userId;

    private String fullName;

    private String idNumber;

    private String phoneNumber;

    private String district;

    private String pradeshiyaSabaha;

    private String email;
}
