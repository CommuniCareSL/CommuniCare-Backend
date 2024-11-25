package com.communicare.CommuniCareBackend.Application.controllers;

import com.communicare.CommuniCareBackend.Application.config.JWTUtilWeb;
import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.Domain.service.UserServicesWEB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerWEB {

    private UserServicesWEB userServicesWEB;

    @Autowired
    private JWTUtilWeb jwtUtil;

    @GetMapping("/by-pradeshiya-sabaha")
    public ResponseEntity<?> getUsersByPradeshiyaSabaha(
            @RequestHeader("Authorization") String token,
            @RequestParam String pradeshiyaSabaha
    ) {
        // Validate the token
        String userEmail;
        try {
            userEmail = jwtUtil.extractUsername(token.substring(7)); // Remove "Bearer " prefix
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }

        // Additional check: Ensure the user has a valid role/permission
        if (!jwtUtil.validateToken(token, userEmail)) {
            return ResponseEntity.status(403).body("Unauthorized access");
        }

        // Fetch users based on pradeshiyaSabaha
        List<User> users = userServicesWEB.getUsersByPradeshiyaSabaha(pradeshiyaSabaha);
        return ResponseEntity.ok(users);
    }
}
