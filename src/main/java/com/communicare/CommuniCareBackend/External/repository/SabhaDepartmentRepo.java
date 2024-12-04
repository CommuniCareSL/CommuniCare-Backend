package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.Department;
import com.communicare.CommuniCareBackend.Domain.entity.Sabha;
import com.communicare.CommuniCareBackend.Domain.entity.SabhaDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SabhaDepartmentRepo extends JpaRepository<SabhaDepartment, Integer> {

    Optional<SabhaDepartment> findBySabhaAndDepartment(Sabha sabha, Department department);
}
