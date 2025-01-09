package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
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
    private int status;  // 0 -Pending, 1 -In Progress, 2 -Resolved, 3-Rejected

    @Column(name = "proofs", columnDefinition = "TEXT")
    private String proofs;  // Store image as binary data

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;  // Automatically stores the current date

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, updatable = false)
    private LocalTime createdTime;  // Automatically stores the current time


}

