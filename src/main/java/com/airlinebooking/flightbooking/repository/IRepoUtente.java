package com.airlinebooking.flightbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airlinebooking.flightbooking.model.Utente;

public interface IRepoUtente extends JpaRepository<Utente, Integer> {
 
	Optional<Utente> findByUsername(String username);
	
	boolean existsByUsername(String username);

	 
}