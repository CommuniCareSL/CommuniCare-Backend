package com.communicare.CommuniCareBackend.Application.dto.response;

//Mobile App

public class SignUpResponse {

    private Integer userId;
    private String message;

    // Modify the constructor to accept Integer userId
    public SignUpResponse(Integer userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
