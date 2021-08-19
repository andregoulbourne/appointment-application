package com.Appointments.Test.Dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Appointments.DTO.AppointmentDTO;

public class AppointmentDTOTest {
	AppointmentDTO appointmentDTO;

	@BeforeEach
	public void setup() {
		this.appointmentDTO = new AppointmentDTO(1, "01303983", false, "Best appointment ever");
	}

	@Test
	public void testGetterAndSetters_setAnotherId() {
		int expectedResult = 1;
		appointmentDTO.setId(1);
		assertEquals(expectedResult, appointmentDTO.getId());
	}

	@Test
	public void testGetterAndSetters_setAnotherDate() {
		String expectedResult = "01303983";
		appointmentDTO.setDate("01303983");
		assertEquals(expectedResult, appointmentDTO.getDate());
	}

	@Test
	public void testGetterAndSetters_setAnotherPassed() {
		boolean expectedResult = true;
		appointmentDTO.setPassed(true);
		assertEquals(expectedResult, appointmentDTO.isPassed());
	}

	@Test
	public void testGetterAndSetters_setAnotherDescription() {
		String expectedResult = "Best appointment ever";
		appointmentDTO.setDescription("Best appointment ever");
		assertEquals(expectedResult, appointmentDTO.getDescription());
	}
}