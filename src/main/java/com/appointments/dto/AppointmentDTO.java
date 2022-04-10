package com.appointments.dto;

import com.appointments.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

private int id;

private String date;

private boolean passed;

private String description;

private  User user;

public AppointmentDTO(String date, boolean passed, String description, User user) {
		this.date = date;
		this.passed = passed;
		this.description = description;
		this.user=user;
	}

}
