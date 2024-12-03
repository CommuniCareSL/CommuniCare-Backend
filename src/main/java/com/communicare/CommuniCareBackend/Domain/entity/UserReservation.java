//package com.communicare.CommuniCareBackend.Domain.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDate;
//
//@Entity
//@Data
//public class UserReservation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int userserviceId;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "serviceId", referencedColumnName = "serviceId", nullable = false)
//    private Service service;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "id_number")
//    private String idNumber;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @Column(nullable = true, columnDefinition = "TEXT")
//    private String event;
//
//    @Column(nullable = true)
//    private LocalDate date;
//
//    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
//    private int status;  // 0 - Reported, 1 - In Progress, 2 - Resolved
//}
//
