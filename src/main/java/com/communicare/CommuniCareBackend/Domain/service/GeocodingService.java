package com.communicare.CommuniCareBackend.Domain.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeocodingService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    public String getAdministrativeAreaLevel4(double latitude, double longitude)
            throws ApiException, InterruptedException, IOException {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        GeocodingResult[] results =  GeocodingApi.reverseGeocode(context, new LatLng(latitude, longitude)).await();

        if (results.length > 0) {
            for (GeocodingResult result : results) {
                for (com.google.maps.model.AddressComponent component : result.addressComponents) {
                    for (String type : component.types) {
                        if ("administrative_area_level_4".equals(type)) {
                            return component.longName;
                        }
                    }
                }
            }
        }

        return null; // Or handle the case where no level 4 is found
    }
}
