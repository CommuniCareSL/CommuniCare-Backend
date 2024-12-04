
package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}

