package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "sabha_id", referencedColumnName = "sabhaId", nullable = false)
    private Sabha sabha;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "ComplaintCategoryId", nullable = false)
    private ComplaintCategory complaintCategory;

    // location (latitude and longitude)
    @Column(columnDefinition = "TEXT")
    private String location;  // Store as 'latitude,longitude' in the format "lat,lng" (e.g. "12.9716,77.5946")

    // location remark
    @Column(nullable = true, columnDefinition = "TEXT")
    private String remark;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int status;  // 0 -Reported, 1 -In Progress, 2 -Resolved

    @Column(name = "proofs", columnDefinition = "TEXT")
    private String proofs;  // Store image as binary data

    
}


