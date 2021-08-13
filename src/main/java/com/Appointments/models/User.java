package com.Appointments.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private List<Appointment> appointments;
	private String phone;
	
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

}
