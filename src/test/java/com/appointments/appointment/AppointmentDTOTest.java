package com.appointments.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.appointments.appointment.AppointmentDTO;
import com.appointments.user.User;

class AppointmentDTOTest {

	@Test
	void testAppointmentDTO_ContructorSetPOJOAndReturnsID() {
		int id = 1;
		AppointmentDTO appointmentDTO = new AppointmentDTO(id, "01303983", false, "Best appointment ever", new User());
		assertNotNull(appointmentDTO);
		assertEquals(id,appointmentDTO.getId());
	}

	private String date = "01303983";
	
	@Test
	void testAppointmentDTO_ContructorSetPOJOAndReturnsDateWithoutId() {
		AppointmentDTO appointmentDTO = new AppointmentDTO(date, false, "Best appointment ever", new User());
		assertNotNull(appointmentDTO);
		assertEquals(date,appointmentDTO.getDate());
	}
	
	@Test
	void testNoArgsConstructor() {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setDate(date);
		assertNotNull(appointmentDTO);
		assertEquals(date,appointmentDTO.getDate());
	}
}