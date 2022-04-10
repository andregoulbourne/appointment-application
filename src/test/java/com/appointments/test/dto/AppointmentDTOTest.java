package com.appointments.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.appointments.dto.AppointmentDTO;
import com.appointments.models.User;

class AppointmentDTOTest {
	AppointmentDTO appointmentDTO;

	@Test
	void testAppointmentDTO_ContructorSetPOJOAndReturnsID() {
		int id = 1;
		this.appointmentDTO = new AppointmentDTO(id, "01303983", false, "Best appointment ever", new User());
		assertNotNull(appointmentDTO);
		assertEquals(id,appointmentDTO.getId());
	}

}