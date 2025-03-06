package com.airlinebooking.flightbooking.service;

import com.airlinebooking.flightbooking.model.Utente;
import com.airlinebooking.flightbooking.model.Ruolo;
import com.airlinebooking.flightbooking.repository.IRepoUtente;
import com.airlinebooking.flightbooking.repository.IRepoRuolo;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class SetupService {
    private final IRepoUtente utenteRepository;
    private final IRepoRuolo ruoloRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SetupService(IRepoUtente utenteRepository, 
                       IRepoRuolo ruoloRepository, 
                       BCryptPasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.ruoloRepository = ruoloRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        setupRoles();
        setupAdmin();
    }

    private void setupRoles() {
        if (!ruoloRepository.findByName("ADMIN").isPresent()) {
            Ruolo adminRole = new Ruolo();
            adminRole.setName("ADMIN");
            ruoloRepository.save(adminRole);
        }
        
        if (!ruoloRepository.findByName("USER").isPresent()) {
            Ruolo userRole = new Ruolo();
            userRole.setName("USER");
            ruoloRepository.save(userRole);
        }
    }

    public void setupAdmin() {
        if (!utenteRepository.existsByUsername("superadmin")) {
            Ruolo adminRole = ruoloRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
            Ruolo userRole = ruoloRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Role USER not found"));

            Utente superAdmin = new Utente();
            superAdmin.setUsername("superadmin");
            superAdmin.setPassword(passwordEncoder.encode("superadmin123"));
            superAdmin.setEnabled(true);

            Set<Ruolo> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);
            superAdmin.setRoles(roles);

            utenteRepository.save(superAdmin);
        }
    }
}