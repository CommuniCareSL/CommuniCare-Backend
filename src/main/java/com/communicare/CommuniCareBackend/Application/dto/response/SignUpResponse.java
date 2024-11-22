package com.communicare.CommuniCareBackend.Application.dto.response;

//Mobile App

public class SignUpResponse {

    private Long userId;
    private String message;

    // Modify the constructor to accept Long userId
    public SignUpResponse(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
