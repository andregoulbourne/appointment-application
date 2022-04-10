package com.appointments.service;

import org.springframework.stereotype.Service;

import com.appointments.dto.AppointmentDTO;
import com.appointments.models.Appointment;

@Service
public class AppointmentService {
	public Appointment appointmentDtoToAppointment(AppointmentDTO appointmentDto) {
		return new Appointment(appointmentDto.getDate(), appointmentDto.isPassed(), appointmentDto.getDescription(), appointmentDto.getUser());
	}
}
