package com.airlinebooking.flightbooking.validation;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

import com.airlinebooking.flightbooking.model.Prenotazione;

import org.springframework.validation.Errors;

@Component
public class PrenotazioneValidator implements Validator {

    private static final String[] VALID_AIRCRAFT = {
        "Boeing 747", "Airbus A380", "Boeing 787", 
        "Airbus A350", "Embraer E190"
    };

    @Override
    public boolean supports(Class<?> clazz) {
        return Prenotazione.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Prenotazione prenotazione = (Prenotazione) target;

        // Validate aircraft name is in allowed list
        if (!Arrays.asList(VALID_AIRCRAFT).contains(prenotazione.getNomeAereo())) {
            errors.rejectValue("nomeAereo", "invalid.aircraft", 
                             "Please select a valid aircraft");
        }

        // Validate total passengers
        int totalPassengers = prenotazione.getnAdulti() + prenotazione.getnBambini();
        if (totalPassengers > getMaxPassengers(prenotazione.getNomeAereo())) {
            errors.rejectValue("nAdulti", "too.many.passengers", 
                             "Total number of passengers exceeds aircraft capacity");
        }
    }

    private int getMaxPassengers(String aircraft) {
        switch (aircraft) {
            case "Boeing 747": return 366;
            case "Airbus A380": return 525;
            case "Boeing 787": return 330;
            case "Airbus A350": return 350;
            case "Embraer E190": return 114;
            default: return 0;
        }
    }
}