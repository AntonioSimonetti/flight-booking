package com.airlinebooking.flightbooking.service;

import com.airlinebooking.flightbooking.model.Utente;
import com.airlinebooking.flightbooking.model.Ruolo;
import com.airlinebooking.flightbooking.repository.IRepoUtente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IRepoUtente utenteRepository;  // Repository per gli utenti

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Recuperiamo l'utente dal database tramite il repository
        Utente utente = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con il nome " + username));

        // Otteniamo i ruoli dell'utente e li trasformiamo in un set di GrantedAuthority
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Ruolo ruolo : utente.getRoles()) {
            String authority = ruolo.getName();
            //authorities.add(new SimpleGrantedAuthority(ruolo.getName()));
            authorities.add(new SimpleGrantedAuthority(authority));
            logger.info("Added authority: {}", authority);
        }

        // Restituiamo un oggetto User con username, password (criptata) e i ruoli dell'utente
        logger.info("User {} has authorities: {}", username, authorities);
        return new User(utente.getUsername(), utente.getPassword(), authorities);
    }
}
