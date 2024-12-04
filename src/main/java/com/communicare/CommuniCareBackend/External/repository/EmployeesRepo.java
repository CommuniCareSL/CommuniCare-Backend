package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Application.dto.EmployeeDTO;
import com.communicare.CommuniCareBackend.Domain.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepo extends JpaRepository<Employees, Integer> {


    Optional<Employees> findByEmail(String email);

    List<Employees> findByRole(String role);

    @Query("SELECT e FROM Employees e JOIN e.sabhaDepartmentId sd WHERE e.role = 'OFFICER'")
    List<Employees> findEmployeesByRoleOfficer();
}
