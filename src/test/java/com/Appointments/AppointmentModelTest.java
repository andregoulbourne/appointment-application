package com.Appointments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Appointments.models.Appointment;

public class AppointmentModelTest {
	Appointment appointment;
	
	@BeforeEach
	public void setup() {
		this.appointment = new Appointment(1, "08-09-2019", false, "My sister freaks out whenever someone takes her phone");
	}
	
	@Test
	public void testGetterAndSetters_setAnotherId(){
		int expectedResult = 2;
		appointment.setId(2);
		assertEquals(expectedResult, appointment.getId());
	}
	
	@Test
	public void testGetterAndSetters_setAnotherDate(){
		String expectedResult = "09-09-2019";
		appointment.setDate("09-09-2019");
		assertEquals(expectedResult, appointment.getDate());
	}
	
	@Test
	public void testGetterAndSetters_setPassedToTrue(){
		boolean expectedResult = true;
		appointment.setPassed(true);
		assertEquals(expectedResult, appointment.isPassed());
	}
	
	@Test
	public void testGetterAndSetters_setAnotherDescription(){
		String expectedResult = "My brother freaks out whenever someone takes his phone";
		appointment.setDescription("My brother freaks out whenever someone takes his phone");
		assertEquals(expectedResult, appointment.getDescription());
	}
	
	@Test
	public void testConstructure_ReturnsNewFields(){
		assertEquals(1, appointment.getId());
		assertEquals("08-09-2019", appointment.getDate());
		assertEquals(false, appointment.isPassed());
		assertEquals("My sister freaks out whenever someone takes her phone", appointment.getDescription());
	}

}
