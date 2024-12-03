package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Application.dto.response.ComplaintDTO;
import com.communicare.CommuniCareBackend.Domain.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Integer> {

//    Complaint getComplaintById(Integer id);
//    List<Complaint> findByHandler_EmployeeId(Integer handlerId);
//
//    List<Complaint> findByDepartment_DepartmentId(Integer departmentId);
//
//    List<Complaint> findByHandler_SabhaDepartmentId(int sabhaDepartmentId);

}
