package com.airlinebooking.flightbooking.model;


import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="prenotazioni") 
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@NotBlank(message = "Flight code is required")
    @Pattern(regexp = "^[A-Z]{2}\\d{4}$", message = "Flight code must be 2 uppercase letters followed by 4 digits")
	@Column(nullable = false)
	String codice_volo;
	
	@Column(nullable = false, precision = 10, scale = 2)
    @NotBlank(message = "Aircraft name is required")
	String nomeAereo;
	
	@Column(nullable = false, precision = 10, scale = 2)
	@NotNull(message = "Price is required")
	@Positive(message = "Price must be greater than 0")
	BigDecimal prezzoBiglietto;
	
	 @Column(nullable = false)
	 @Min(value = 1, message = "At least one adult is required")
	 int nAdulti;
	    
	 @Column(nullable = false)
	 @PositiveOrZero(message = "Number of children cannot be negative")
	 int nBambini;
	
	@ManyToOne
	@JoinColumn(name = "utente_id", nullable = false) 
	private Utente utente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice_volo() {
		return codice_volo;
	}

	public void setCodice_volo(String codice_volo) {
		this.codice_volo = codice_volo;
	}

	public String getNomeAereo() {
		return nomeAereo;
	}

	public void setNomeAereo(String nomeAereo) {
		this.nomeAereo = nomeAereo;
	}

	public BigDecimal getPrezzoBiglietto() {
		return prezzoBiglietto;
	}

	public void setPrezzoBiglietto(BigDecimal prezzoBiglietto) {
		this.prezzoBiglietto = prezzoBiglietto;
	}

	public int getnAdulti() {
		return nAdulti;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void setnAdulti(int nAdulti) {
		this.nAdulti = nAdulti;
	}

	public int getnBambini() {
		return nBambini;
	}

	public void setnBambini(int nBambini) {
		this.nBambini = nBambini;
	}
		
}
