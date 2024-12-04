package com.communicare.CommuniCareBackend.Application.dto.response;

import lombok.Data;

@Data
public class ComplaintUpdateRequest {
    private String status;
    private String remark;
    private String rejectionReason;

    // Getters and Setters
}
