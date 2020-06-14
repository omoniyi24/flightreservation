package com.jayjav.flightreservation.flightreservation.controller;

import com.jayjav.flightreservation.flightreservation.entities.Flight;
import com.jayjav.flightreservation.flightreservation.entities.User;
import com.jayjav.flightreservation.flightreservation.repository.FlightRepository;
import com.jayjav.flightreservation.flightreservation.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping(value = "/findFlights", method = RequestMethod.POST)
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
                        @RequestParam("departure") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate,
                        ModelMap modelMap){
        LOGGER.info("Inside findFlights() From {} TO: {} Departure Date {} ", from, to, departureDate);
        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
//        List<Flight> flights = flightRepository.findAll();
        System.out.println(">>>>" + flights.toString());
        modelMap.addAttribute("flights", flights);
        LOGGER.info("Flight Found are: {}", flights.toString());
        return "displayFlights";
    }

    @RequestMapping("admin/showAddFlight")
    public String showAddFlight(){
        return "addFlight";
    }

    
}
