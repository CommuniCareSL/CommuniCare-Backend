package com.communicare.CommuniCareBackend.Application.controllers;


import com.yourproject.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    private GeocodingService geocodingService;

    @GetMapping("/api/location")
    public String getLocationDetails(@RequestParam double latitude,
                                     @RequestParam double longitude)
            throws ApiException, InterruptedException, IOException {

        String adminAreaLevel4 = geocodingService.getAdministrativeAreaLevel4(latitude, longitude);
        return "Administrative Area Level 4: " + adminAreaLevel4;
    }
}
