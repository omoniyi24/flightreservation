package com.jayjav.flightreservation.flightreservation.repository;

import com.jayjav.flightreservation.flightreservation.entities.Reservation;
import com.jayjav.flightreservation.flightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
