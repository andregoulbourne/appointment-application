package com.Appointments.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.Appointments.exceptions.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3668688024203637785L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String pwd;
	private String firstName;
	private String lastName;
	private String middleName;
	@OneToMany(mappedBy="user")
	private List<Appointment> appointments;
	private String phone;
	private boolean admin;
	private boolean vendor;
	
	public void addAppointment(Appointment appointment) {
		List<Appointment> newListAppointments = new ArrayList<>(appointments);
		newListAppointments.add(appointment);
		this.appointments = newListAppointments;
	}
	
	public void removeAppointmentById(int id) {
		List<Appointment> newListAppointments = new ArrayList<>(appointments);
		for(Appointment appointment: appointments) {
			if(appointment.getId() == id) {
				newListAppointments.remove(appointment);
			}
		}
		this.appointments = newListAppointments;
	}
	
	public Appointment getAppointmentById(int id) {
		for(Appointment appointment: appointments) {
			if(appointment.getId() == id) {
				return appointment;
			}
		}
		
		throw new NotFoundException("Sorry but there is no appointment associated to this user with the id of " + id);
		
	}

	public User(String username, String pwd, String firstName, String lastName, String middleName,
			List<Appointment> appointments, String phone) {
		super();
		this.id = 0;
		this.username = username;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.appointments = appointments;
		this.phone = phone;
		this.admin = false;
		this.vendor = false;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", appointments=" + appointments + ", phone=" + phone + "]";
	}
	
	

}