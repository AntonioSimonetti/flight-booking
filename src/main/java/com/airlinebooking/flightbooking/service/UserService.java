package com.airlinebooking.flightbooking.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.airlinebooking.flightbooking.model.*;
import com.airlinebooking.flightbooking.repository.*;

@Service
public class UserService {
    private final IRepoUtente userRepository;
    private final IRepoRuolo roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(IRepoUtente userRepository, 
                      IRepoRuolo roleRepository, 
                      BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(Utente user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        
        Set<Ruolo> roles = new HashSet<>();
        roles.add(roleRepository.findByName("USER").orElseThrow());
        user.setRoles(roles);
        
        userRepository.save(user);
    }
}
