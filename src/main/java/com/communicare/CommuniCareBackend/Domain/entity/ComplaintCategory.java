package com.communicare.CommuniCareBackend.Domain.entity;

import com.communicare.CommuniCareBackend.Application.dto.response.ComplaintDTO;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import com.communicare.CommuniCareBackend.Application.dto.response.ComplaintUpdateRequest;
import com.communicare.CommuniCareBackend.Domain.entity.*;
import com.communicare.CommuniCareBackend.External.repository.*;

@Entity
@Data
public class ComplaintCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintCategoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Long getComplaintCategoryId() {
        return complaintCategoryId;
    }

    public void setComplaintCategoryId(Long complaintCategoryId) {
        this.complaintCategoryId = complaintCategoryId;
    }

    public String getCategoryName() {
        return name;
    }

    public void setCategoryName(String categoryName) {
        this.name = categoryName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
