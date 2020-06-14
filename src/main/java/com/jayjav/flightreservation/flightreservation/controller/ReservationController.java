package com.jayjav.flightreservation.flightreservation.controller;

import com.jayjav.flightreservation.flightreservation.dto.ReservationRequest;
import com.jayjav.flightreservation.flightreservation.entities.Flight;
import com.jayjav.flightreservation.flightreservation.entities.Reservation;
import com.jayjav.flightreservation.flightreservation.repository.FlightRepository;
import com.jayjav.flightreservation.flightreservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationService reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){
        LOGGER.info("showCompleteReservation() invoked with  Flight Number: {}", flightId);
        Flight flight = flightRepository.findById(flightId).get();
        modelMap.addAttribute("flight", flight);
        LOGGER.info("Flight is: {}", flight);
        return "completeReservation";
    }

    @RequestMapping("/completeReservation")
    public String completeReservation(ReservationRequest request, ModelMap modelMap){
        LOGGER.info("completeReservation() invoked with  request: {}", request.toString());
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg", "Reservation Created Successfully with ID " + reservation.getId());
        return "reservationConfirmation";
    }
}
