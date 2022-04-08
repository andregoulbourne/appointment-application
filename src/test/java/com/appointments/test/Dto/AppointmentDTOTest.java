package com.appointments.test.Dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.appointments.DTO.AppointmentDTO;

class AppointmentDTOTest {
	AppointmentDTO appointmentDTO;

	@BeforeEach
	void setup() {
		this.appointmentDTO = new AppointmentDTO(1, "01303983", false, "Best appointment ever");
	}

	@Test
	void testGetterAndSetters_setAnotherId() {
		int expectedResult = 1;
		appointmentDTO.setId(1);
		assertEquals(expectedResult, appointmentDTO.getId());
	}

	@Test
	void testGetterAndSetters_setAnotherDate() {
		String expectedResult = "01303983";
		appointmentDTO.setDate("01303983");
		assertEquals(expectedResult, appointmentDTO.getDate());
	}

	@Test
	void testGetterAndSetters_setAnotherPassed() {
		boolean expectedResult = true;
		appointmentDTO.setPassed(true);
		assertEquals(expectedResult, appointmentDTO.isPassed());
	}

	@Test
	void testGetterAndSetters_setAnotherDescription() {
		String expectedResult = "Best appointment ever";
		appointmentDTO.setDescription("Best appointment ever");
		assertEquals(expectedResult, appointmentDTO.getDescription());
	}
}