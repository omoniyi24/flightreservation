package com.jayjav.flightreservation.flightreservation.service;

import com.jayjav.flightreservation.flightreservation.dto.ReservationRequest;
import com.jayjav.flightreservation.flightreservation.entities.Reservation;

public interface ReservationService {

    Reservation bookFlight(ReservationRequest reservationRequest);
}
