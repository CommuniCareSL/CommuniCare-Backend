package com.communicare.CommuniCareBackend.Application.controllers;
//Mobile App

import com.communicare.CommuniCareBackend.Domain.service.UserService;
import com.communicare.CommuniCareBackend.Application.dto.request.SignUpRequest;
import com.communicare.CommuniCareBackend.Application.dto.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//Mobile App

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@Validated @RequestBody SignUpRequest signUpRequest) {
        SignUpResponse response = userService.registerUser(signUpRequest);
        return ResponseEntity.ok(response);
    }
}
