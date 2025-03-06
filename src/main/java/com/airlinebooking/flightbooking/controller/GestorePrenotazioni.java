package com.airlinebooking.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.airlinebooking.flightbooking.model.Prenotazione;
import com.airlinebooking.flightbooking.model.Utente;
import com.airlinebooking.flightbooking.repository.IRepoPrenotazione;
import com.airlinebooking.flightbooking.repository.IRepoUtente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class GestorePrenotazioni {

    private static final Logger logger = LoggerFactory.getLogger(GestorePrenotazioni.class);

    @Autowired
    IRepoPrenotazione irp;

    @Autowired
    IRepoUtente repoUtente;
    
    @PostMapping("/upsert")
    public String upsert(@ModelAttribute Prenotazione prenotazione, Model model) {
        logger.info("Richiesta di upsert ricevuta.");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        logger.info("Utente autenticato: {}", username);

        Utente utente = repoUtente.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        // Check if it's an update or new booking
        if (prenotazione.getId() != null) {
            Prenotazione existingPrenotazione = irp.findById(prenotazione.getId())
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));

            // Update existing booking
            existingPrenotazione.setCodice_volo(prenotazione.getCodice_volo());
            existingPrenotazione.setNomeAereo(prenotazione.getNomeAereo());
            existingPrenotazione.setPrezzoBiglietto(prenotazione.getPrezzoBiglietto());
            existingPrenotazione.setnAdulti(prenotazione.getnAdulti());
            existingPrenotazione.setnBambini(prenotazione.getnBambini());

            irp.save(existingPrenotazione);
            logger.info("Prenotazione modificata con successo");
        } else {
            // New booking
            prenotazione.setUtente(utente);
            irp.save(prenotazione);
            logger.info("Nuova prenotazione aggiunta con successo");
        }

        return "redirect:/readAll";
    }

    @GetMapping("/readAll")
    public String readAll(Model model) {
        // Recupera l'utente autenticato
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            // Log per visualizzare il nome utente autenticato
            logger.info("Utente autenticato per leggere tutte le prenotazioni: {}", username);
            
            // Recupera i ruoli dell'utente
            StringBuilder roles = new StringBuilder();
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                roles.append(authority.getAuthority()).append(" ");  // Concatenare tutti i ruoli
            }
            // Log per visualizzare i ruoli dell'utente
            logger.info("Ruoli dell'utente: {}", roles.toString().trim());


            // Recupera l'utente dal DB
            Utente utente = repoUtente.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

            // Recupera le prenotazioni associate a questo utente
            List<Prenotazione> prenotazioni = irp.findByUtente(utente);
            model.addAttribute("prenotazioni", prenotazioni);

            // Ritorna la vista con le prenotazioni
            return "table";
        }

        // Se non troviamo l'utente autenticato, lanciamo un'eccezione
        throw new RuntimeException("Errore nell'autenticazione");
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        irp.deleteById(id);
        return "redirect:/readAll";
    }
    
    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        // Recupera la prenotazione dal DB
        Prenotazione prenotazione = irp.findById(id)
            .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));

        // Aggiunge la prenotazione al modello per precompilare il form
        model.addAttribute("prenotazione", prenotazione);

        // Ritorna alla stessa JSP del form
        return "form";
    }

}
