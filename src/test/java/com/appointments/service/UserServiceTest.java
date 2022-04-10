package com.appointments.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.appointments.dto.UserDTO;
import com.appointments.models.User;

@RunWith(SpringRunner.class)
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
