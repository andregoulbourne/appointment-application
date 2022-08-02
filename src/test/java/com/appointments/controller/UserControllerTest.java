package com.appointments.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	UserController controller;
	
    IUser repo;
    
    UserService service;
    
    @BeforeEach
    void setup() {
    	repo = Mockito.mock(IUser.class);
    	service = Mockito.mock(UserService.class);
    }
    
    //Using this number assuming it an unused id
    private int id = 99;
    
    @Test
    void testPostUser() {
    	UserDTO userDto = new UserDTO();
    	User user = new User();
    	user.setId(id);
    	Mockito.when(service.userDtoToUser(userDto)).thenReturn(user);
    	Mockito.when(repo.save(user)).thenReturn(user);
    	
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	ReflectionTestUtils.setField(controller, "service", service);
    	
    	assertDoesNotThrow(() -> controller.postUser(userDto));
    	User resp = controller.postUser(userDto);
    	
    	assertNotNull(resp);
    	assertEquals(id,resp.getId());
    }
    
    @Test
    void testgetAll() {
    	List<User> users = new ArrayList<>();
    	User user = new User();
    	users.add(user);
    	
    	Mockito.when(repo.findAll()).thenReturn(users);
    	
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	
    	assertDoesNotThrow(() -> controller.getAll());
    	List<User> resp = controller.getAll();
    	
    	assertNotNull(resp);
    	assertEquals(1,resp.size());
    	assertEquals(user,resp.get(0));
    }
    
    @Test
    void testgetUser() {
    	assertDoesNotThrow(() -> controller.getUser(id));
    	User resp = controller.getUser(id);
    	assertNull(resp);
    	
    	User user1 =new User();
    	user1.setId(id);
    	Optional<User> user = Optional.of(user1);
    	
    	Mockito.when(repo.findById(id)).thenReturn(user);
    	
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	
    	assertDoesNotThrow(() -> controller.getUser(id));
    	resp = controller.getUser(id);
    	
    	assertNotNull(resp);
    	assertEquals(id,resp.getId());
    	
    	UserDTO userDto = new UserDTO();
    	String emailId = "test@test.com";
    	String pwd = "pwd";
    	userDto.setEmailId(emailId);
    	userDto.setPwd(pwd);
    	
    	user1.setEmailId(emailId);
    	user1.setPwd(pwd);
    	
    	Mockito.when(repo.findByEmailId(emailId)).thenReturn(user1);
    	
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	
    	assertDoesNotThrow(() -> controller.getUser(userDto));
    	resp = controller.getUser(userDto);
    	
    	assertNotNull(resp);
    	assertEquals(id,resp.getId());
    	
    	//login failure case
    	userDto.setPwd("aDifferentPwd");
    	assertDoesNotThrow(() -> controller.getUser(userDto));
    	resp = controller.getUser(userDto);
    	assertNull(resp);
    	
    }
    
    @Test
    void testputUser() {
    	
    	
    	String username = "kitty";
    	UserDTO userDTO = new UserDTO(1,username,"hello","Damian","Marley","Zion","emailId",new ArrayList<>(),"5008889999", true, true);
    	
    	assertDoesNotThrow(() -> controller.putUser(userDTO));
    	
    	User user1 =new User();
    	user1.setId(1);
    	Optional<User> user = Optional.of(user1);
    	
    	Mockito.when(repo.findById(1)).thenReturn(user);
    	
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	
    	assertDoesNotThrow(() -> controller.putUser(userDTO));
    	
    	UserDTO userDTO1 = null;
    	
    	assertDoesNotThrow(() -> controller.putUser(userDTO1));
    	User resp = controller.putUser(userDTO1);
    	assertNull(resp);
    }
    
    @Test
    void testdeleteUser() {
    	assertDoesNotThrow(() -> controller.deleteUser(id));
    }

}
