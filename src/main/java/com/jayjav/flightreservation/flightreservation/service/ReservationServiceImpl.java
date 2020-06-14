package com.jayjav.flightreservation.flightreservation.service;

import com.jayjav.flightreservation.flightreservation.dto.ReservationRequest;
import com.jayjav.flightreservation.flightreservation.entities.Flight;
import com.jayjav.flightreservation.flightreservation.entities.Passenger;
import com.jayjav.flightreservation.flightreservation.entities.Reservation;
import com.jayjav.flightreservation.flightreservation.repository.FlightRepository;
import com.jayjav.flightreservation.flightreservation.repository.PassengerRepository;
import com.jayjav.flightreservation.flightreservation.repository.ReservationRepository;
import com.jayjav.flightreservation.flightreservation.util.EmailUtil;
import com.jayjav.flightreservation.flightreservation.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Value("${com.jayjav.flightreservation.itinerary.dirpath}")
    private String ITINERARY_DIR;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    EmailUtil emailUtil;

    @Override
    @Transactional
    public Reservation bookFlight(ReservationRequest reservationRequest) {
        LOGGER.info("bookFlight()");

        //Make Payment 

        Long flightId = reservationRequest.getFlightId();
        LOGGER.info("Fetching flight for flight id {}", flightId);
        Flight flight = flightRepository.findById(flightId).get();

        Passenger passenger = new Passenger();
        passenger.setFirstName(reservationRequest.getPassengerFirstName());
        passenger.setLastName(reservationRequest.getPassengerLastName());
        passenger.setPhone(reservationRequest.getPassengerPhone());
        passenger.setEmail(reservationRequest.getPassengerEmail());
        LOGGER.info("Saving the passenger: {}", passenger.toString());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        LOGGER.info("Saving the reservation: {}", reservation.toString());
        Reservation savedReservation = reservationRepository.save(reservation);

        String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
        LOGGER.info("Generating the itinerary");
        pdfGenerator.generateItinerary(savedReservation, filePath);
        LOGGER.info("Emailing the itinerary");
        emailUtil.sendItinerary(passenger.getEmail(), filePath);
        return savedReservation;
    }
}
