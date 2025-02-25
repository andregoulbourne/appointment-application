package com.appointments.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.appointments.dao.IUser;
import com.appointments.model.User;
import com.appointments.model.UserDTO;
import com.appointments.service.UserService;

@SpringBootTest
class UserControllerTest {
	
	@Autowired
	public 	UserControllerTest(UserController controller){
		this.controller = controller;
	}

	UserController controller;
    
    private final int id = 1;
    
    @Test
    void testPostUser() {
    	UserDTO userDto = new UserDTO();
    	User resp = controller.postUser(userDto);
    	
    	assertNotNull(resp);
    	assertTrue(resp.getId() > 0);


		assertThrows(IllegalStateException.class, () -> controller.postUser(userDto));
    }


    
    @Test
    void testgetUser() {
    	UserDTO userDto = new UserDTO();
    	String emailId = "test@test.com";
    	String pwd = "SA";
    	userDto.setEmailId(emailId);
    	userDto.setPwd(pwd);

    	User resp = controller.getUser(userDto);
    	
    	assertNotNull(resp);
    	assertEquals(id,resp.getId());
    	
    	//login failure case
    	userDto.setPwd("aDifferentPwd");
    	assertDoesNotThrow(() -> controller.getUser(userDto));
    	resp = controller.getUser(userDto);
    	assertNull(resp);
    	
    }

}
