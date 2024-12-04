package com.communicare.CommuniCareBackend.Domain.service;

//Mobile App
import com.communicare.CommuniCareBackend.Application.config.JWTUtilApp;
import com.communicare.CommuniCareBackend.Application.dto.request.LoginRequest;
import com.communicare.CommuniCareBackend.Application.dto.response.LoginResponse;
import com.communicare.CommuniCareBackend.Application.dto.response.OfficerProfile;
import com.communicare.CommuniCareBackend.Domain.entity.Employees;
import com.communicare.CommuniCareBackend.Domain.entity.Sabha;
import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.External.repository.EmployeesRepo;
import com.communicare.CommuniCareBackend.External.repository.SabhaRepository;
import com.communicare.CommuniCareBackend.External.repository.UserRepository;
import com.communicare.CommuniCareBackend.Application.dto.request.SignUpRequest;
import com.communicare.CommuniCareBackend.Application.dto.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SabhaRepository sabhaRepository;

    @Autowired
    private EmployeesRepo employeesRepo;

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

        // Find the Sabha entity based on the ID passed in the request
        Optional<Sabha> sabhaOptional = sabhaRepository.findById(signUpRequest.getSabhaId());
        if (sabhaOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid Sabha ID.");
        }

        Sabha sabha = sabhaOptional.get();

        User user = new User();
        user.setFullName(signUpRequest.getFullName());
        user.setIdNumber(signUpRequest.getIdNumber());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setDistrict(signUpRequest.getDistrict());
        user.setSabha(sabha); // Associate the Sabha object
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setIsBlock(0);  // Set default value for isBlock

        User savedUser = userRepository.save(user);

        return new SignUpResponse(savedUser.getUserId(), "User registered successfully.");
    }

    @Autowired
    private JWTUtilApp jwtUtil;

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        if (user.getIsBlock() == 1) {
            throw new IllegalArgumentException("User is blocked");
        }

        // Add user details to the JWT claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("fullName", user.getFullName());
        claims.put("idNumber", user.getIdNumber());
        claims.put("sabahaId", user.getSabha().getSabhaId());
        // or if you want to add the name of the Sabha
        // claims.put("pradeshiyaSabahaName", user.getPradeshiyaSabaha().getSabhaName());

        // Generate JWT token
        String token = jwtUtil.generateToken(claims, user.getEmail());

        return new LoginResponse(token, "Login successful");
    }

    public OfficerProfile getProfileByEmail(String email) {
        Optional<Employees> optionalEmployee = employeesRepo.findByEmail(email);
        if (optionalEmployee.isPresent()) {
            Employees employee = optionalEmployee.get();
            OfficerProfile profile = new OfficerProfile();
            profile.setEmployeeId(employee.getEmployeeId());
            profile.setEmail(employee.getEmail());
            profile.setAddress(employee.getAddress());
            profile.setNic(employee.getNic());
            profile.setDistrict(employee.getDistrict());
            profile.setSabaha(employee.getSabaha());
            profile.setName(employee.getName());
            profile.setRole(employee.getRole());
            profile.setSabhaDepartmentId(employee.getSabhaDepartmentId().getSabhaDepartmentId()); // Assuming getId() returns the ID
            return profile;
        } else {
            throw new RuntimeException("Employee not found with email: " + email);
        }
    }


//without JWT
//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    public String loginUser(String email, String password) {
//        // Check if the user exists by email
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
//
//        // Check if the password is correct
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new IllegalArgumentException("Invalid email or password.");
//        }
//
//        // Check if the user is blocked
//        if (user.getIsBlock() == 1) {
//            throw new IllegalStateException("This account is blocked. Please contact support.");
//        }
//
//        // If all checks pass, return a success message or token
//        return "Login successful!";
//    }
}
