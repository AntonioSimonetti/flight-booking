package com.airlinebooking.flightbooking.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="utenti")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, unique=true)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		 name = "user_roles",
		 joinColumns = @JoinColumn(name = "user_id"),
		 inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Ruolo> roles;
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Ruolo> getRoles() {
		return roles;
	}

	public void setRoles(Set<Ruolo> roles) {
		this.roles = roles;
	}

	
}
