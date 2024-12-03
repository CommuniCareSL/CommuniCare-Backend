package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Application.dto.response.OfficerProfile;
import com.communicare.CommuniCareBackend.Domain.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import com.communicare.CommuniCareBackend.Domain.entity.SabhaDepartment;

import java.util.Optional;

public interface EmployeesRepo extends JpaRepository<Employees, Integer> {


    Optional<Employees> findByEmail(String email);

}
