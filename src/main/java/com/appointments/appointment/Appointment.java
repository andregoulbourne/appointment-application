package com.appointments.appointment;

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

import com.appointments.user.User;

@Entity
@Table(name = "appointment")
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
	private String scheduled;
	
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
		this.scheduled = date;
		this.passed = passed;
		this.description = description;
		this.user = user;
	}

	public Appointment(int id, @NotEmpty @NotNull String scheduled, @NotNull boolean passed,
			@NotEmpty @NotNull String description, User user) {
		super();
		this.id = id;
		this.scheduled = scheduled;
		this.passed = passed;
		this.description = description;
		this.user = user;
	}

	public Appointment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScheduled() {
		return scheduled;
	}

	public void setScheduled(String scheduled) {
		this.scheduled = scheduled;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}