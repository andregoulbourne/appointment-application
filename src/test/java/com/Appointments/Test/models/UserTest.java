package com.Appointments.Test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Appointments.exceptions.NotFoundException;
import com.Appointments.models.Appointment;
import com.Appointments.models.User;

public class UserTest {

	User user;

	@BeforeEach
	public void setup() {
		List<Appointment> appointments = new ArrayList<>();
		Appointment newAppointment = new Appointment(5, "08-09-2019", false, "My sister freaks out whenever someone takes her phone", user);
		appointments.add(newAppointment);
		this.user = new User(1, "patient", "password", "firstName", "lastName", "middleName", appointments,
				"19735678888");
	}

	@Test
	public void testGetterAndSetters_setAnotherId() {
		int expectedResult = 3;
		user.setId(3);
		assertEquals(expectedResult, user.getId());
	}

	@Test
	public void  testGetterAndSetters_setAnotherUsername(){
	  String expectedResult = "ladiesMan123";       
	user.setUsername("ladiesMan123");
	 assertEquals(expectedResult, user.getUsername());
	 }

	public void testGetterAndSetters_setAnotherPwd() {
		String expectedResult = "IlikeKittens123";
		user.setPwd("IlikeKittens123");
		assertEquals(expectedResult, user.getPwd());
	}

	@Test
	public void testGetterAndSetters_setAnotherFirstName() {
		String expectedResult = "Andre";
		user.setFirstName("Andre");
		assertEquals(expectedResult, user.getFirstName());
	}

	@Test
	public void testGetterAndSetters_setAnotherLastName() {
		String expectedResult = "Goulbourne";
		user.setLastName("Goulbourne");
		assertEquals(expectedResult, user.getLastName());
	}

	@Test
	public void testGetterAndSetters_setAnotherMiddleName() {
		String expectedResult = "Markela";
		user.setMiddleName("Markela");
		assertEquals(expectedResult, user.getMiddleName());
	}

	@Test
	public void testGetterAndSetters_setAnotherPhone() {
		String expectedResult = "187645652";
		user.setPhone("187645652");
		assertEquals(expectedResult, user.getPhone());
	}
	
	@Test
	public void testAddAppointmentAndRetrieveAppointment() {
		Appointment newAppointment = new Appointment(1, "08-09-2019", false, "My sister freaks out whenever someone takes her phone", user);
		user.addAppointment(newAppointment);
		assertEquals(newAppointment, user.getAppointmentById(1));
	}
	
	@Test
	public void testRemoveAppointment_throwsAppointmentNotFoundException() {
		user.removeAppointmentById(5);
		Exception exception = assertThrows(NotFoundException.class, () -> {
			user.getAppointmentById(5);
	    });
		
		String expectedMessage = "Sorry but there is no appointment associated to this user with the id of ";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

}