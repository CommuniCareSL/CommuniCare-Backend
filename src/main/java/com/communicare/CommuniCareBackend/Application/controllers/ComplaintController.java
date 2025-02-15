package com.communicare.CommuniCareBackend.Application.controllers;


import com.communicare.CommuniCareBackend.Domain.entity.Complaint;
import com.communicare.CommuniCareBackend.Domain.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/api/user/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<?> submitComplaint(@RequestBody Map<String, Object> complaintData) {
        try {
            Complaint complaint = complaintService.processComplaintData(complaintData);
            return ResponseEntity.ok(complaint);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getComplaints(
            @RequestParam int sabhaId,
            @RequestParam int departmentId) {
        List<Complaint> complaints = complaintService.getComplaints(sabhaId, departmentId);
        return ResponseEntity.ok(complaints);
    }
}