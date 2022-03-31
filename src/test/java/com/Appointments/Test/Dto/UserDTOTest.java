package com.Appointments.Test.Dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Appointments.DTO.UserDTO;
import com.Appointments.models.Appointment;

class UserDTOTest {
	UserDTO userDTO;

	@BeforeEach
	void setup() {
		this.userDTO = new UserDTO("kitty","hello","Damian","Marley","Zion",new ArrayList<>(),"5008889999");
	}

	@Test
	void testGetterAndSetters_setAnotherId() {
		int expectedResult = 1;
		userDTO.setId(1);
		assertEquals(expectedResult, userDTO.getId());
	}

	@Test
	void testGetterAndSetters_setAnotherUsername() {
		String expectedResult = "kitty";
		userDTO.setUsername("kitty");
		assertEquals(expectedResult, userDTO.getUsername());
	}

	@Test
	void testGetterAndSetters_setAnotherPwd() {
		String expectedResult = "hello";
		userDTO.setPwd("hello");
		assertEquals(expectedResult, userDTO.getPwd());
	}

	@Test
	void testGetterAndSetters_setAnotherFirstName() {
		String expectedResult = "Damian";
		userDTO.setFirstName("Damian");
		assertEquals(expectedResult, userDTO.getFirstName());
	}

	@Test
	void testGetterAndSetters_setAnotherLastName() {
		String expectedResult = "Marley";
		userDTO.setLastName("Marley");
		assertEquals(expectedResult, userDTO.getLastName());
	}

	@Test
	void testGetterAndSetters_setAnotherMiddleName() {
		String expectedResult = "Zion";
		userDTO.setMiddleName("Zion");
		assertEquals(expectedResult, userDTO.getMiddleName());
	}

	@Test
	void  testGetterAndSetters_setAnotherAppointments(){ 
	  List<Appointment> expectedResult = new ArrayList<>(); 
	  userDTO.setAppointments(new ArrayList<>()); 
	  assertEquals(expectedResult, userDTO.getAppointments());      
	 }

	@Test
	void testGetterAndSetters_setAnotherPhone() {
		String expectedResult = "5008889999";
		userDTO.setPhone("5008889999");
		assertEquals(expectedResult, userDTO.getPhone());
	}
}