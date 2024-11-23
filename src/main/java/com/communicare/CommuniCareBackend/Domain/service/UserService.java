package com.communicare.CommuniCareBackend.Domain.service;

//Mobile App
import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.External.repository.UserRepository;
import com.communicare.CommuniCareBackend.Application.dto.request.SignUpRequest;
import com.communicare.CommuniCareBackend.Application.dto.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SignUpResponse registerUser(SignUpRequest signUpRequest) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(signUpRequest.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        Optional<User> existingUserByIdNumber = userRepository.findByIdNumber(signUpRequest.getIdNumber());
        if (existingUserByIdNumber.isPresent()) {
            throw new IllegalArgumentException("ID Number already exists.");
        }

        User user = new User();
        user.setFullName(signUpRequest.getFullName());
        user.setIdNumber(signUpRequest.getIdNumber());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setDistrict(signUpRequest.getDistrict());
        user.setPradeshiyaSabaha(signUpRequest.getPradeshiyaSabaha());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setIsBlock(0);  // Set default value for isBlock

        User savedUser = userRepository.save(user);

        return new SignUpResponse(savedUser.getUserId(), "User registered successfully.");
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public String loginUser(String email, String password) {
        // Check if the user exists by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        // Check if the password is correct
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        // Check if the user is blocked
        if (user.getIsBlock() == 1) {
            throw new IllegalStateException("This account is blocked. Please contact support.");
        }

        // If all checks pass, return a success message or token
        return "Login successful!";
    }
}
