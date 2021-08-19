package com.Appointments.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment implements Serializable { 
/**
	 * 
	 */
	private static final long serialVersionUID = 3671025459362668461L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@NotEmpty
	@NotNull
	private String date;
	
	@NotNull
	private boolean passed;
	
	@NotEmpty
	@NotNull
	private String description; 
	
	@ManyToOne
	@JoinColumn(name="users_id", nullable=false)
	private User user;

	public Appointment(@NotEmpty @NotNull String date, @NotNull boolean passed, @NotEmpty @NotNull String description,
			User user) {
		super();
		this.date = date;
		this.passed = passed;
		this.description = description;
		this.user = user;
	}

}