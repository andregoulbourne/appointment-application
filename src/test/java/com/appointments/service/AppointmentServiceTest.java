package com.appointments.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.appointments.dto.AppointmentDTO;
import com.appointments.models.Appointment;
import com.appointments.models.User;

@SpringBootTest
class AppointmentServiceTest {

	@Autowired
	AppointmentService service;
	 
	@Test
	void testappointmentDtoToAppointment() {
		int id = 1;
		AppointmentDTO appointmentDTO = new AppointmentDTO(id, "01303983", false, "Best appointment ever", new User());
		Appointment appointment = service.appointmentDtoToAppointment(appointmentDTO);
		assertEquals(appointmentDTO.getDate(), appointment.getScheduled());
	}
	
}