package com.Appointments.DTO;

import java.util.List;

import com.Appointments.models.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private int id;

	private String username;

	private String pwd;

	private String firstName;

	private String lastName;

	private String middleName;

	private List<Appointment> appointments;

	private String phone;
	
	private boolean admin;
	
	private boolean vendor;

	public UserDTO(String username, String pwd, String firstName, String lastName, String middleName,
			List<Appointment> appointments, String phone) {
		this.username = username;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.appointments = appointments;
		this.phone = phone;
	}

}
