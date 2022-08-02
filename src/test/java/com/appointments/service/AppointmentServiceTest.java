package com.appointments.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.appointments.model.Appointment;
import com.appointments.model.AppointmentDTO;
import com.appointments.model.User;
import com.appointments.service.AppointmentService;

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
