package com.jayjav.flightreservation.flightreservation.controller;


import com.jayjav.flightreservation.flightreservation.dto.ReservationUpdateRequest;
import com.jayjav.flightreservation.flightreservation.entities.Reservation;
import com.jayjav.flightreservation.flightreservation.repository.ReservationRepository;
import com.jayjav.flightreservation.flightreservation.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class ReservationRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRESTController.class);

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        LOGGER.info("Inside findReservation() for ID: {}", id);
        return reservationRepository.findById(id).get();
    }

    @RequestMapping("/reservations")
    public Reservation updateReservstion(@RequestBody ReservationUpdateRequest reservationUpdateRequest){
        LOGGER.info("Inside updateReservstion() for {}", reservationUpdateRequest.toString());
        Reservation reservation = reservationRepository.findById(reservationUpdateRequest.getId()).get();
        reservation.setNumberOfBags(reservationUpdateRequest.getNumberOfBags());
        reservation.setCheckedIn(reservationUpdateRequest.getCheckedIn());
        LOGGER.info("Saving Reservation");
        return reservationRepository.save(reservation);
    }
}
