package com.airlinebooking.flightbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airlinebooking.flightbooking.model.*;

public interface IRepoRuolo extends JpaRepository<Ruolo, Integer> {
 
    Optional<Ruolo> findByName(String name);
}