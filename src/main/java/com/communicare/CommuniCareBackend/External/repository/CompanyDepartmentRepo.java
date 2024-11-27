package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.CompanyDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDepartmentRepo extends JpaRepository<CompanyDepartment, Integer> {
}
