package com.airlinebooking.flightbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.airlinebooking.flightbooking.model.*;
import com.airlinebooking.flightbooking.service.UserService;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Utente());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Utente user) {
        userService.registerNewUser(user);
        return "redirect:/login";
    }
}