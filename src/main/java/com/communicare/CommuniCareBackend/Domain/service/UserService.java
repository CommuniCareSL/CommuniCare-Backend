package com.communicare.CommuniCareBackend.Domain.service;

//Mobile App
import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.External.repository.UserRepository;
import com.communicare.CommuniCareBackend.Application.dto.request.SignUpRequest;
import com.communicare.CommuniCareBackend.Application.dto.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
}
