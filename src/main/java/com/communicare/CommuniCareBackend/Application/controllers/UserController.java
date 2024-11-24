package com.communicare.CommuniCareBackend.Application.controllers;
//Mobile App

import com.communicare.CommuniCareBackend.Application.dto.response.UserResponse;
import com.communicare.CommuniCareBackend.Domain.service.UserService;
import com.communicare.CommuniCareBackend.Application.dto.request.SignUpRequest;
import com.communicare.CommuniCareBackend.Application.dto.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //web application
    // New endpoint to get users by pradeshiyaSabaha
    @GetMapping("/pradeshiyaSabaha/{pradeshiyaSabaha}")
    public ResponseEntity<List<UserResponse>> getUsersByPradeshiyaSabaha(@PathVariable String pradeshiyaSabaha) {
        List<UserResponse> users = userService.getUsersByPradeshiyaSabaha(pradeshiyaSabaha);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // Returns 204 No Content if no users are found
        }
        return ResponseEntity.ok(users);
    }
}
