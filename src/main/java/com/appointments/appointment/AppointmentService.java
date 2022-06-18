package com.appointments.appointment;

import org.springframework.stereotype.Service;

import com.appointments.appointment.AppointmentDTO;
import com.appointments.appointment.Appointment;

@Service
public class AppointmentService {
	public Appointment appointmentDtoToAppointment(AppointmentDTO appointmentDto) {
		return new Appointment(appointmentDto.getDate(), appointmentDto.isPassed(), appointmentDto.getDescription(), appointmentDto.getUser());
	}
}
