package com.appointments.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import com.appointments.dto.UserDTO;

class UserDTOTest {
	UserDTO userDTO;

	@Test
	void testUserDTO_ContructorNotNullReturnsUsername() {
		String username = "kitty";
		this.userDTO = new UserDTO(username,"hello","Damian","Marley","Zion",new ArrayList<>(),"5008889999");
		assertNotNull(userDTO);
		assertEquals(username, userDTO.getUsername());
	}

}