package com.communicare.CommuniCareBackend.Application.controllers;

import com.communicare.CommuniCareBackend.Application.dto.EmployeeDTO;
import com.communicare.CommuniCareBackend.Application.dto.ReqRes;
import com.communicare.CommuniCareBackend.Domain.entity.Employees;
import com.communicare.CommuniCareBackend.Domain.service.EmployeesManagementService;
import com.communicare.CommuniCareBackend.External.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeManagementController {

    @Autowired
    private EmployeesManagementService employeesManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg) {
        return ResponseEntity.ok(employeesManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(employeesManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(employeesManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(employeesManagementService.getAllUsers());

    }

    @GetMapping("/adminuser/get-officers-with-department")
    public ResponseEntity<List<EmployeeDTO>> getAllOfficers() {
        List<EmployeeDTO> employeeDTOs = employeesManagementService.getAllOfficers();
        return ResponseEntity.ok(employeeDTOs);
    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(employeesManagementService.getUsersById(userId));

    }

    @GetMapping("/adminuser/get-officers")
    public ResponseEntity<List<Employees>> getOfficers() {
        List<Employees> officers = employeesManagementService.getOfficers();
        return ResponseEntity.ok(officers);
    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody Employees reqres){
        return ResponseEntity.ok(employeesManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = employeesManagementService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{employeeId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer employeeId){
        return ResponseEntity.ok(employeesManagementService.deleteUser(employeeId));
    }
}
