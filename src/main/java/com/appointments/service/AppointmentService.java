package com.appointments.service;

import com.appointments.model.User;
import org.springframework.stereotype.Service;

import com.appointments.model.Appointment;
import com.appointments.model.AppointmentDTO;

@Service
public class AppointmentService {
	public Appointment appointmentDtoToAppointment(AppointmentDTO appointmentDto) {
		return new Appointment(appointmentDto.getDate(), appointmentDto.isPassed(), appointmentDto.getDescription(), appointmentDto.getUser());
	}

	public boolean isAppointmentExistingForUser(User user, int appointmentId) {
		long countExisingAppointment = user.getAppointments().stream()
				.filter(appointment1 -> appointment1.getId() == appointmentId)
				.count();

		System.out.println(countExisingAppointment);

		return countExisingAppointment > 0;
	}
}
