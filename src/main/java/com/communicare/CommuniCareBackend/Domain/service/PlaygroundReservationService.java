package com.communicare.CommuniCareBackend.Domain.service;

import com.communicare.CommuniCareBackend.Domain.entity.UserReservation;
import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.Domain.entity.Reservation;
import com.communicare.CommuniCareBackend.External.repository.UserRepository;
import com.communicare.CommuniCareBackend.External.repository.UserReservationRepository;
import com.communicare.CommuniCareBackend.External.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlaygroundReservationService {

    private static final Logger logger = LoggerFactory.getLogger(PlaygroundReservationService.class);

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final UserReservationRepository userReservationRepository;

    public UserReservation processReservationData(Map<String, Object> reservationData) throws Exception {
        // Extract and log data
        int userId = (int) reservationData.get("userId");
        int reservationId = (int) reservationData.get("reservationId");
        logger.info("Processing reservation for user ID: {}, reservation ID: {}", userId, reservationId);

        // Fetch user and reservation entities
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User with ID {} not found", userId);
                    return new Exception("No User found with ID: " + userId);
                });

        Reservation reservationEntity = reservationRepository.findById(reservationId)
                .orElseThrow(() -> {
                    logger.error("Reservation with ID {} not found", reservationId);
                    return new Exception("No Reservation found with ID: " + reservationId);
                });

        // Create and populate UserReservation entity
        UserReservation userReservation = new UserReservation();
        userReservation.setUser(user);
        userReservation.setReservation(reservationEntity);
        userReservation.setName((String) reservationData.get("name"));
        userReservation.setIdNumber((String) reservationData.get("idNumber"));
        userReservation.setPhoneNumber((String) reservationData.get("phoneNumber"));
        userReservation.setEvent((String) reservationData.getOrDefault("event", ""));
        userReservation.setDate(LocalDate.parse((String) reservationData.get("date")));
        userReservation.setStatus(0);

        logger.info("Saving reservation for user ID: {}", userId);
        return userReservationRepository.save(userReservation);
    }
}











