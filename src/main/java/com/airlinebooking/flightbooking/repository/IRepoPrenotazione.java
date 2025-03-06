package com.airlinebooking.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airlinebooking.flightbooking.model.Prenotazione;
import com.airlinebooking.flightbooking.model.Utente;

public interface IRepoPrenotazione extends JpaRepository<Prenotazione, Integer> {
	
	List<Prenotazione> findByUtente(Utente utente);
}
