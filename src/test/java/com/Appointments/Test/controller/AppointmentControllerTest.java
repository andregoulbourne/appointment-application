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

import com.Appointments.controllers.AppointmentController;
import com.Appointments.models.Appointment;
import com.Appointments.models.User;
import com.Appointments.repo.IAppointment;
import com.Appointments.repo.IUser;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IAppointment repo;
	
	@MockBean 
	private IUser urepo;

	Optional<Appointment> appointment;
	
	User user;
	
	Appointment appointment2;
	
	@BeforeEach
	void setup() {
		this.user = new User(1, "patient2", "password2", "firstName2", "lastName2", "middleName2", new ArrayList<>(),
				"19735678882", false, false);
		this.appointment = Optional.of(new Appointment(1, "08-09-2019", false, "My sister freaks out whenever someone takes her phone", user));
		this.appointment2 = new Appointment(2, "08-09-2019", false, "My brother freaks out whenever someone takes his phone", user);
		given(urepo.save(user)).willReturn(user);
	}
	
	@Test
	void givenAppointment_whenPostAppointment_thenReturnJsonAppointment() throws Exception {
		given(repo.save(appointment2)).willReturn(appointment2);
		
		mvc.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(appointment2)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void givenAppointments_whenGetAppointments_thenReturnJsonArray() throws Exception {
		List<Appointment> allAppointments = Arrays.asList(appointment2);

		given(repo.findAll()).willReturn(allAppointments);

		mvc.perform(get("/appointments").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].date", is(appointment2.getDate())));
	}
	
	@Test
	void givenAppointment_WhenGetAppointment_ThenReturnJsonAppointment() throws Exception {
		given(repo.findById(1)).willReturn(appointment);
		
		 mvc.perform(get("/appointments/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.date", is(appointment.get().getDate())));
	}
	
	@Test
	void testUpdate() throws Exception {
		given(repo.save(appointment2)).willReturn(appointment2);
		Appointment appointment3 =new Appointment(3, "08-09-2019", false, "My sister freaks out whenever someone takes her phone", user);
		given(repo.save(appointment3)).willReturn(appointment3);
		given(repo.findById(1)).willReturn(Optional.of(appointment3));

		
		mvc.perform(put("/appointments/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(appointment3)))
				.andDo(print())
				.andExpect(status().isOk());
		
	  }
	
	@Test
	void testDelete() throws Exception {
		given(repo.findById(1)).willReturn(appointment);
		mvc.perform(delete("/appointments/1"))
			.andExpect(status().isOk());
	}
	
	
}