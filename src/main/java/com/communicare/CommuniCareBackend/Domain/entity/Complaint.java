package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    @Lob
    @Column
    private byte[] proofs;  // Store image as binary data


    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "sabha_id", referencedColumnName = "sabhaId", nullable = false)
    private Sabha sabha;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "ComplaintCategoryId", nullable = false)
    private ComplaintCategory complaintCategory;

    
}


