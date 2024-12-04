package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.UserReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReservationRepository extends JpaRepository<UserReservation, Integer> {
}


