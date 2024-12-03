package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
   // List<Complaint> findBySabha_SabhaIdAndComplaintCategory_Department_DepartmentId(int sabhaId, Long departmentId);
   List<Complaint> findByComplaintCategory_ComplaintCategoryIdIn(List<Long> categoryIds);

}
