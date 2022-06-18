package com.appointments.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.appointments.exceptions.NotFoundException;

import com.appointments.appointment.Appointment;

@Entity
@Table(name = "users")
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
	private String emailId;
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
	
	public User(int id, String username, String pwd, String firstName, String lastName, String middleName,
			String emailId, List<Appointment> appointments, String phone, boolean admin, boolean vendor) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.emailId = emailId;
		this.appointments = appointments;
		this.phone = phone;
		this.admin = admin;
		this.vendor = vendor;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", appointments=" + appointments + ", phone=" + phone + "]";
	}

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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isVendor() {
		return vendor;
	}

	public void setVendor(boolean vendor) {
		this.vendor = vendor;
	}

}