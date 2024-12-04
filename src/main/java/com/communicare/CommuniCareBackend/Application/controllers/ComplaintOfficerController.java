package com.communicare.CommuniCareBackend.Application.controllers;

import com.communicare.CommuniCareBackend.Application.dto.response.ComplaintDTO;
import com.communicare.CommuniCareBackend.Application.dto.response.ComplaintUpdateRequest;
import com.communicare.CommuniCareBackend.Domain.entity.Complaint;
import com.communicare.CommuniCareBackend.Domain.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import com.communicare.CommuniCareBackend.Domain.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintOfficerController {
    @Autowired
    private ComplaintService complaintServiceOther;

    private final ComplaintService complaintService;

    public ComplaintOfficerController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/department")
    public ResponseEntity<List<ComplaintDTO>> getComplaintsForDepartmentAndSabha() {
        List<ComplaintDTO> complaintDetails = complaintService.getComplaintDetailsForDepartmentAndSabha();
        return ResponseEntity.ok(complaintDetails);
    }

    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable Integer id) {
        return complaintService.getComplaintById(id);
    }


    // Get complaint details by ID
    @GetMapping("/{complaintid}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long complaintid) {
        Complaint complaint = complaintService.getComplaintById(Math.toIntExact(complaintid));
        return ResponseEntity.ok(complaint);
    }

    // Update complaint status, remark, and rejection reason
    @PutMapping("/{complaintid}/status")
    public ResponseEntity<Complaint> updateComplaintStatus(
            @PathVariable Long complaintid,
            @RequestBody ComplaintUpdateRequest updateRequest) {

        Complaint updatedComplaint = complaintService.updateComplaintStatus(complaintid, updateRequest);
        return ResponseEntity.ok(updatedComplaint);
    }
}