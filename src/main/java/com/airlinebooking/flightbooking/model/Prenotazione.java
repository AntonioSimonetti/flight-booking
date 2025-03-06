package com.airlinebooking.flightbooking.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name="prenotazioni") 
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(nullable = false)
	String codice_volo;
	
	@Column(nullable = false)
	String nomeAereo;
	
	@Column(nullable = false, precision = 10, scale = 2)
	BigDecimal prezzoBiglietto;
	
	@Column(nullable = false)
	int nAdulti;
	
	@Column(nullable = false)
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
