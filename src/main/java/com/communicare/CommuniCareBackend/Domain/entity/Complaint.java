package com.communicare.CommuniCareBackend.Domain.entity;

import jakarta.persistence.*;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    @Column(nullable = false)
    private String description;

    @Lob
    @Column
    private byte[] proofs;  // Store image as binary data

    @Column(nullable = false)
    private String location;

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

