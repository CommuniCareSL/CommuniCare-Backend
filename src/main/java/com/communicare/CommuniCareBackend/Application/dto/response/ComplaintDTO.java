package com.communicare.CommuniCareBackend.Application.dto.response;


import com.communicare.CommuniCareBackend.Domain.entity.Status;
import lombok.Data;

@Data
public class ComplaintDTO {
    private int complaintId;
    private String description;
    private byte[] proofs;
    private String userName;
    private String sabhaName;
    private String categoryName;
    private Status status;
    private String handlerName;
    private String departmentName;
}
