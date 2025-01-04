package com.communicare.CommuniCareBackend.Domain.service;

import com.communicare.CommuniCareBackend.Application.dto.ReqRes;
import com.communicare.CommuniCareBackend.Domain.entity.Department;
import com.communicare.CommuniCareBackend.Domain.entity.Employees;
import com.communicare.CommuniCareBackend.Domain.entity.Sabha;
import com.communicare.CommuniCareBackend.Domain.entity.SabhaDepartment;
import com.communicare.CommuniCareBackend.External.repository.DepartmentRepository;
import com.communicare.CommuniCareBackend.External.repository.SabhaDepartmentRepo;
import com.communicare.CommuniCareBackend.External.repository.EmployeesRepo;
import com.communicare.CommuniCareBackend.External.repository.SabhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeesManagementService {

    @Autowired
    private EmployeesRepo employeesRepo;

    @Autowired
    private SabhaDepartmentRepo sabhaDepartmentRepo;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SabhaRepository sabhaRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            Employees ourUser = new Employees();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setDistrict(registrationRequest.getDistrict());
//            ourUser.setSabaha(registrationRequest.getSabaha());
            Sabha sabha =  sabhaRepository
                    .findById(registrationRequest.getSabhaId())
                    .orElseThrow(() -> new RuntimeException("Invalid Sabha ID"));
            ourUser.setSabha(sabha);

            ourUser.setRole(registrationRequest.getRole());
            ourUser.setName(registrationRequest.getName());
            ourUser.setAddress(registrationRequest.getAddress());
            ourUser.setNic(registrationRequest.getNic());
            // Fetch SabhaDepartment by ID
//            SabhaDepartment sabhaDepartment = sabhaDepartmentRepo
//                    .findById(registrationRequest.getSabhaDepartmentId())
//                    .orElseThrow(() -> new RuntimeException("Invalid sabha department ID"));
//            ourUser.setSabhaDepartmentId(sabhaDepartment);
            Department department =  departmentRepository
                    .findById(registrationRequest.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Invalid department ID"));
            ourUser.setDepartment(department);

            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            Employees ourEmployeesResult = employeesRepo.save(ourUser);
            if (ourEmployeesResult.getEmployeeId()>0) {
                resp.setOurEmployees((ourEmployeesResult));
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = employeesRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        try{
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            Employees employees = employeesRepo.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), employees)) {
                var jwt = jwtUtils.generateToken(employees);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<Employees> result = employeesRepo.findAll();
            if (!result.isEmpty()) {
                reqRes.setOurEmployeesList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }


    public ReqRes getUsersById(Integer id) {
        ReqRes reqRes = new ReqRes();
        try {
            Employees employeesById = employeesRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setOurEmployees(employeesById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Employees with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes deleteUser(Integer employeeId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Employees> userOptional = employeesRepo.findById(employeeId);
            if (userOptional.isPresent()) {
                employeesRepo.deleteById(employeeId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, Employees updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Employees> userOptional = employeesRepo.findById(userId);
            if (userOptional.isPresent()) {
                Employees existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setDistrict(updatedUser.getDistrict());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                Employees savedUser = employeesRepo.save(existingUser);
                reqRes.setOurEmployees(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes getMyInfo(String email){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Employees> userOptional = employeesRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setOurEmployees(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }
}
