package com.appointments.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.appointments.user.UserDTO;

class UserDTOTest {
	UserDTO userDTO;

	@Test
	void testUserDTO_ContructorNotNullReturnsUsername() {
		String username = "kitty";
		this.userDTO = new UserDTO(username,"hello","Damian","Marley","Zion",new ArrayList<>(),"5008889999");
		assertNotNull(userDTO);
		assertEquals(username, userDTO.getUsername());
	}
	
	@Test
	void testUserDTO_ContructorNotNullReturnsUsernameEmpty() {
		String username = "kitty";
		this.userDTO = new UserDTO();
		userDTO.setUsername(username);
		assertNotNull(userDTO);
		assertEquals(username, userDTO.getUsername());
	}
	
	@Test
	void testUserDTO_ContructorNotNullReturnsUsernameAllArgs() {
		String username = "kitty";
		this.userDTO = new UserDTO(1,username,"hello","Damian","Marley","Zion","emailId",new ArrayList<>(),"5008889999", true, true);
		assertNotNull(userDTO);
		assertEquals(username, userDTO.getUsername());
	}

}