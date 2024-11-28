package com.communicare.CommuniCareBackend.Application.controllers;
//Mobile App

import com.communicare.CommuniCareBackend.Application.dto.request.LoginRequest;
import com.communicare.CommuniCareBackend.Application.dto.response.LoginResponse;
import com.communicare.CommuniCareBackend.Domain.service.UserService;
import com.communicare.CommuniCareBackend.Application.dto.request.SignUpRequest;
import com.communicare.CommuniCareBackend.Application.dto.response.SignUpResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//Mobile App
@Slf4j
@RestController
@RequestMapping("/api/app/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@Validated @RequestBody SignUpRequest signUpRequest) {
        SignUpResponse response = userService.registerUser(signUpRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }
//without jwt
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
//        log.info("Login request");
//        String message = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
//        return ResponseEntity.ok(new LoginResponse(message));
//    }
}
