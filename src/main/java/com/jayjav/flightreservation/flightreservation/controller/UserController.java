package com.jayjav.flightreservation.flightreservation.controller;

import com.jayjav.flightreservation.flightreservation.entities.User;
import com.jayjav.flightreservation.flightreservation.repository.UserRepository;
import com.jayjav.flightreservation.flightreservation.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/showReg")
    public String showRegistrationPage(){
        LOGGER.info("Inside showRegistrationPage");
        return "login/registerUser";
    }

    @RequestMapping("/showLogin")
    public String showLogin(){
        LOGGER.info("Inside showLogin()");
        return "login/login";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user){
        LOGGER.info("Inside registerUser() with parameter {}", user.toString());
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){
        LOGGER.info("Inside login() with email {}", email);
        boolean loginResponse = securityService.login(email, password);
        if(loginResponse){
            return "findFlights";
        }else {
            modelMap.addAttribute("msg","Invalid Username or Password");
        }
        return "login/login";
    }


}
