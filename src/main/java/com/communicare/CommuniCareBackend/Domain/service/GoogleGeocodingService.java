package com.communicare.CommuniCareBackend.Domain.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GoogleGeocodingService {

    private static final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String API_KEY = "AIzaSyBz4axrGtEMC2pyJ3xJ7hVf9TsfvFe3L0E";

    public String getAdminAreaLevel4(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();

        // Correctly format the URL
        String url = String.format("%s?latlng=%f,%f&key=%s", API_URL, latitude, longitude, API_KEY);

        // Fetch response as a map
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("results")) {
            return null;
        }

        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
        for (Map<String, Object> result : results) {
            List<Map<String, Object>> addressComponents = (List<Map<String, Object>>) result.get("address_components");
            for (Map<String, Object> component : addressComponents) {
                List<String> types = (List<String>) component.get("types");
                if (types.contains("locality")) {
                    return (String) component.get("long_name");
                }
            }
        }

        return null; // Return null if no match is found
    }
}
