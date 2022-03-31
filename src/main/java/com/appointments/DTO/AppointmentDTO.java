package com.appointments.DTO;

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

public AppointmentDTO(String date, boolean passed, String description) {
		this.date = date;
		this.passed = passed;
		this.description = description;
	}

}
