package com.jayjav.flightreservation.flightreservation.repository;

import com.jayjav.flightreservation.flightreservation.entities.Passenger;
import com.jayjav.flightreservation.flightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
