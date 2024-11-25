package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ComplaintCategory complaintCategory;
}
