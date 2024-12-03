package com.communicare.CommuniCareBackend.Application.controllers;

import com.communicare.CommuniCareBackend.Application.dto.response.ComplaintDTO;
import com.communicare.CommuniCareBackend.Domain.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintOfficerController {

//    @Autowired
    private final ComplaintService complaintService;

    public ComplaintOfficerController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/department")
    public ResponseEntity<List<ComplaintDTO>> getComplaintsForDepartmentAndSabha() {
        List<ComplaintDTO> complaintDetails = complaintService.getComplaintDetailsForDepartmentAndSabha();
        return ResponseEntity.ok(complaintDetails);
    }
}
