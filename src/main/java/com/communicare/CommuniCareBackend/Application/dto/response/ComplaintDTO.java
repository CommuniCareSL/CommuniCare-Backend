package com.communicare.CommuniCareBackend.Application.dto.response;

import lombok.Data;

@Data
public class ComplaintDTO {
    private Integer complaintId;
    private String complaintDescription;
    private String location;
    private String status;
    private String departmentName;
    private String sabhaName;
    private String employeeName;
    private String categoryName; // Adding category name to DTO
    private String proofs;
    private String createdDate;
    private String createdTime;
    private String userName; // Adding user name to DTO
}
