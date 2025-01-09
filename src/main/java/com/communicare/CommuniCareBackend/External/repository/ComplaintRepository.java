package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    //get all complints pass sabhaId and departmentId
    @Query("SELECT c FROM Complaint c " +
            "JOIN c.complaintCategory cc " +
            "JOIN cc.department d " +
            "WHERE c.sabha.sabhaId = :sabhaId " +
            "AND d.departmentId = :departmentId")
    List<Complaint> findComplaintsBySabhaAndDepartment(@Param("sabhaId") int sabhaId,
                                                       @Param("departmentId") int departmentId);
}
