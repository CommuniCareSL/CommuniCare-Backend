package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import com.communicare.CommuniCareBackend.Domain.entity.SabhaDepartment;

import java.util.Optional;

public interface EmployeesRepo extends JpaRepository<Employees, Integer> {


    Optional<Employees> findByEmail(String email);

   // Optional<Employees> findByDepartmentAndRole(SabhaDepartment department, String role);

   //  Optional<Employees> findBySabhaDepartmentId(Long sabhaDepartmentId);

//    Optional<Employees> findBySabhaDepartmentId(SabhaDepartment sabhaDepartmentId);
//    Optional<Employees> findBySabhaDepartment(SabhaDepartment sabhaDepartment);

}
