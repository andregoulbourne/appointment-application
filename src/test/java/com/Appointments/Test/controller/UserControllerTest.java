package com.Appointments.Test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.Appointments.controllers.UserController;
import com.Appointments.models.User;
import com.Appointments.repo.IUser;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IUser repo;

	Optional<User> user;
	
	User user2;
	
	@BeforeEach
	void setup() {
		this.user = Optional.of(new User(1, "patient", "password", "firstName", "lastName", "middleName", new ArrayList<>(),
				"19735678888"));
		this.user2 = new User("patient2", "password2", "firstName2", "lastName2", "middleName2", new ArrayList<>(),
				"19735678882");
	}
	
	@Test
	void givenUser_whenPostUser_thenReturnJsonUser() throws Exception {
		given(repo.save(user2)).willReturn(user2);
		
		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user2)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {
		
		List<User> allUsers = Arrays.asList(user2);

		given(repo.findAll()).willReturn(allUsers);

		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].firstName", is(user2.getFirstName())));
	}
	
	@Test
	public void givenUser_WhenGetUser_ThenReturnJsonUser() throws Exception {
		
		given(repo.findById(1)).willReturn(user);
		
		 mvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName", is("firstName")));
	}
	
	@Test
	void testUpdate() throws Exception {
		given(repo.save(user2)).willReturn(user2);
		User user3 =new User(0, "patient", "password", "firstName", "lastName", "middleName", new ArrayList<>(),
				"19735678888");
		given(repo.save(user3)).willReturn(user3);
		given(repo.findById(0)).willReturn(Optional.empty());
		given(repo.findById(1)).willReturn(Optional.of(user3));

		mvc.perform(put("/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(user2)))
			.andDo(print())
			.andExpect(status().isOk());
		
		mvc.perform(put("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user3)))
				.andDo(print())
				.andExpect(status().isOk());
		
	  }
	
	@Test
	void testDelete() throws Exception {
		given(repo.findById(1)).willReturn(user);
		mvc.perform(delete("/users/1"))
			.andExpect(status().isOk());
	}
	
	
}
