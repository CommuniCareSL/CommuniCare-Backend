package com.communicare.CommuniCareBackend.Application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String message;
}

//without JWT
//public class LoginResponse {
//
//    private String message;
//
//    public LoginResponse(String message) {
//        this.message = message;
//    }
//
//    // Getter
//    public String getMessage() {
//        return message;
//    }
//}
