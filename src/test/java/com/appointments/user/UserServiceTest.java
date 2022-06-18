package com.appointments.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.appointments.user.UserDTO;
import com.appointments.user.User;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	UserService service;
	
	@Test
	void testuserDtoToUser() {
		String username = "kitty";
		UserDTO userDTO = new UserDTO(username,"hello","Damian","Marley","Zion",new ArrayList<>(),"5008889999");
		User user = service.userDtoToUser(userDTO);
		assertEquals(userDTO.getUsername(), user.getUsername());
	}
}
