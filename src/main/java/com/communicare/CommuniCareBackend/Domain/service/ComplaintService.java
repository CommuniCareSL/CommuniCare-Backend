package com.communicare.CommuniCareBackend.Domain.service;


import com.communicare.CommuniCareBackend.Domain.entity.Complaint;
import com.communicare.CommuniCareBackend.Domain.entity.ComplaintCategory;
import com.communicare.CommuniCareBackend.Domain.entity.Sabha;
import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.External.repository.ComplaintCategoryRepository;
import com.communicare.CommuniCareBackend.External.repository.ComplaintRepository;
import com.communicare.CommuniCareBackend.External.repository.SabhaRepository;
import com.communicare.CommuniCareBackend.External.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final UserRepository userRepository; // Inject UserRepository
    private final GoogleGeocodingService googleGeocodingService;
    private final ExcelProcessorService excelProcessorService;
    private final SabhaRepository sabhaRepository;
    private final ComplaintRepository complaintRepository;
    private final ComplaintCategoryRepository complaintCategoryRepository; // Add this repository

    public Complaint processComplaintData(Map<String, Object> complaintData) throws Exception {
        // Step 1: Extract latitude, longitude, and categoryId
        double latitude = (double) complaintData.get("latitude");
        double longitude = (double) complaintData.get("longitude");
        int categoryId = (int) complaintData.get("categoryId");

        // Step 2: Get the administrative_area_level_4 name
        String adminAreaLevel4 = googleGeocodingService.getAdminAreaLevel4(latitude, longitude);

        // Step 3: Match admin4Name_en to admin3_id in the Excel file
        Integer admin3Id = excelProcessorService.findAdmin3IdByAdmin4Name(adminAreaLevel4);
        if (admin3Id == null) {
            throw new Exception("No matching admin3_id found for admin4Name: " + adminAreaLevel4);
        }

        // Log admin3Id type
        System.out.println("admin3Id type: " + admin3Id.getClass().getName());

        // Find Sabha
        Sabha sabha = sabhaRepository.findById(admin3Id)
                .orElseThrow(() -> new Exception("No Sabha found with ID: " + admin3Id));

        // Log Sabha ID type
        System.out.println("Sabha ID type: " + sabha.getSabhaId());

        // Step 5: Get the user
        int userId = (int) complaintData.get("userId");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("No User found with ID: " + userId));
        // Extract the userId and convert it to UUID
//        String userIdStr = (String) complaintData.get("userId"); // Expecting a string representation of UUID
//        UUID userId = UUID.fromString(userIdStr); // Convert to UUID
//
//        // Fetch the user
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new Exception("No User found with ID: " + userId));


        // Step 5: Fetch ComplaintCategory by categoryId
        ComplaintCategory complaintCategory = complaintCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new Exception("No ComplaintCategory found with ID: " + categoryId));

        // Step 6: Map data to Complaint entity
        Complaint complaint = new Complaint();
        complaint.setSabha(sabha);
        complaint.setComplaintCategory(complaintCategory);
        complaint.setLocation(latitude + "," + longitude);
        complaint.setRemark(adminAreaLevel4);
        complaint.setDescription((String) complaintData.get("description"));
        complaint.setUser(user);

        // Save Base64 image string as a string in the database
        List<String> images = (List<String>) complaintData.get("images");
        if (images != null && !images.isEmpty()) {
            complaint.setProofs(images.get(0)); // Assuming you want to save the first image
        }

        // Step 7: Save the Complaint entity
        return complaintRepository.save(complaint);
    }

    public List<Complaint> getComplaintsByStatus(int status) {
        return complaintRepository.findByStatus(status);
    }
}