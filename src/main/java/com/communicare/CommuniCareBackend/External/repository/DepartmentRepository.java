package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
