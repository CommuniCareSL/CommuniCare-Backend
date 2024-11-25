package com.communicare.CommuniCareBackend.Domain;

import com.communicare.CommuniCareBackend.Domain.entity.ComplaintCategory;
import com.communicare.CommuniCareBackend.Domain.entity.Department;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DepartmentComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentComplaintId;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ComplaintCategory complaintCategory;
}
