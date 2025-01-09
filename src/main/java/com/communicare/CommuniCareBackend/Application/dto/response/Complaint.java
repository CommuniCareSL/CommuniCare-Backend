package com.communicare.CommuniCareBackend.Application.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class Complaint {

    private int complaintId;
    private int userId;
    private int complaintCategoryId;
    private String complaintCategoryName;
    private String remark;
    private int status;
    private LocalDate createdDate;
    private LocalTime createdTime;

    public Complaint(int complaintId, int userId, int complaintCategoryId, String complaintCategoryName, String remark, int status, LocalDate createdDate, LocalTime createdTime) {
        this.complaintId = complaintId;
        this.userId = userId;
        this.complaintCategoryId = complaintCategoryId;
        this.complaintCategoryName = complaintCategoryName;
        this.remark = remark;
        this.status = status;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
    }

    // Getters and setters (optional if using Lombok)
}