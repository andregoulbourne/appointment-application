package com.Appointments.Test.Dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Appointments.DTO.UserDTO;
import com.Appointments.models.Appointment;

public class UserDTOTest {
	UserDTO userDTO;

	@BeforeEach
	public void setup() {
		this.userDTO = new UserDTO("kitty","hello","Damian","Marley","Zion",new ArrayList<>(),"5008889999");
	}

	@Test
	public void testGetterAndSetters_setAnotherId() {
		int expectedResult = 1;
		userDTO.setId(1);
		assertEquals(expectedResult, userDTO.getId());
	}

	@Test
	public void testGetterAndSetters_setAnotherUsername() {
		String expectedResult = "kitty";
		userDTO.setUsername("kitty");
		assertEquals(expectedResult, userDTO.getUsername());
	}

	@Test
	public void testGetterAndSetters_setAnotherPwd() {
		String expectedResult = "hello";
		userDTO.setPwd("hello");
		assertEquals(expectedResult, userDTO.getPwd());
	}

	@Test
	public void testGetterAndSetters_setAnotherFirstName() {
		String expectedResult = "Damian";
		userDTO.setFirstName("Damian");
		assertEquals(expectedResult, userDTO.getFirstName());
	}

	@Test
	public void testGetterAndSetters_setAnotherLastName() {
		String expectedResult = "Marley";
		userDTO.setLastName("Marley");
		assertEquals(expectedResult, userDTO.getLastName());
	}

	@Test
	public void testGetterAndSetters_setAnotherMiddleName() {
		String expectedResult = "Zion";
		userDTO.setMiddleName("Zion");
		assertEquals(expectedResult, userDTO.getMiddleName());
	}

	@Test
	public void  testGetterAndSetters_setAnotherAppointments(){ 
	  List<Appointment> expectedResult = new ArrayList<>(); 
	  userDTO.setAppointments(new ArrayList<>()); 
	  assertEquals(expectedResult, userDTO.getAppointments());      
	 }

	@Test
	public void testGetterAndSetters_setAnotherPhone() {
		String expectedResult = "5008889999";
		userDTO.setPhone("5008889999");
		assertEquals(expectedResult, userDTO.getPhone());
	}
}