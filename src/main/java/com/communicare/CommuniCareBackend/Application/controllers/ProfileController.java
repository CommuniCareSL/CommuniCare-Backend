package com.communicare.CommuniCareBackend.Application.controllers;

import com.communicare.CommuniCareBackend.Application.config.JWTUtilApp;
import com.communicare.CommuniCareBackend.Application.dto.response.OfficerProfile;
import com.communicare.CommuniCareBackend.Domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/officer")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtilApp jwtUtilApp;

    @GetMapping("/profile")
    public ResponseEntity<?> getOfficerProfile(@RequestHeader("Authorization") String token) {
        // Extract token and validate
        String jwt = token.replace("Bearer ", "");
        String email = jwtUtilApp.extractUsername(jwt);


        // Fetch profile details from service
        OfficerProfile profile = userService.getProfileByEmail(email);

        return ResponseEntity.ok(profile);
    }
}

