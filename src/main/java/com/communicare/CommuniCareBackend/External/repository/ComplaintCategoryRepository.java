package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.ComplaintCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintCategoryRepository extends JpaRepository<ComplaintCategory, Integer> {
    List<ComplaintCategory> findByDepartment_DepartmentId(Long departmentId);
}
