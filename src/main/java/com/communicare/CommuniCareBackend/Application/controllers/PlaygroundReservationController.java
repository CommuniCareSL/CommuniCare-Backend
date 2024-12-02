package com.communicare.CommuniCareBackend.Application.controllers;

import com.communicare.CommuniCareBackend.Domain.entity.UserReservation;
import com.communicare.CommuniCareBackend.Domain.service.PlaygroundReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app/api/user/reservations")
@RequiredArgsConstructor
public class PlaygroundReservationController {

    private final PlaygroundReservationService playgroundReservationService;

    @PostMapping
    public ResponseEntity<?> submitReservation(@RequestBody Map<String, Object> reservationData) {
        try {
            UserReservation userReservation = playgroundReservationService.processReservationData(reservationData);
            return ResponseEntity.ok(Map.of("message", "Reservation submitted successfully", "reservation", userReservation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error: " + e.getMessage()));
        }
    }
}


