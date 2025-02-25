package com.appointments.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.appointments.model.Session;
import com.appointments.service.AppointmentService;
import com.appointments.service.SessionService;
import com.appointments.util.EncryptionUtility;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.appointments.dao.IUser;
import com.appointments.model.Appointment;
import com.appointments.model.AppointmentDTO;
import com.appointments.model.User;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppointmentsControllerTest {

	@Autowired
	public AppointmentsControllerTest(AppointmentController controller, EncryptionUtility encryptionUtility, AppointmentService appointmentService, SessionService sessionService){
		this.controller = controller;
		this.encryptionUtility = encryptionUtility;
		this.appointmentService = appointmentService;

		String emailId = "test@test.com";
		token = encryptionUtility.encrypt(emailId);

		User user = new User();
		user.setId(1);
		user.setEmailId(emailId);

		Session session = new Session();
		session.setToken(token);
		session.setUser(user);

		sessionService.addSessionToCache(session);
	}

	AppointmentController controller;

	EncryptionUtility encryptionUtility;

	AppointmentService appointmentService;

    private final int id = 1;
	private final String description = "description";

	private String token;

    @Test
	@Order(1)
    void testpostAppointment() {
		AppointmentDTO appointmentDto = new AppointmentDTO();
		appointmentDto.setId(id);
		appointmentDto.setDate("2025-02-25");
		appointmentDto.setDescription(description);
		User user = new User();
		user.setId(1);
		appointmentDto.setUser(user);

    	
    	Appointment resp = controller.postAppointment(token, appointmentDto);
    	
    	assertEquals(id,resp.getId());
    }
    
    @Test
	@Order(2)
    void testgetAll() {
    	Appointment appointment = new Appointment();

    	assertDoesNotThrow(() -> controller.getAll(token));
    	List<Appointment> resp = controller.getAll(token);
    	
    	assertNotNull(resp);
    	assertEquals(1,resp.size());
    	assertEquals(description, resp.get(0).getDescription());
    }
    
    @Test
	@Order(3)
    void testgetAppointment() {
    	assertDoesNotThrow(() -> controller.getAppointment(token,0));
    	Appointment resp = controller.getAppointment(token, 0);
    	assertNull(resp);
    	
    	Appointment appointment1 =new Appointment();
    	appointment1.setId(id);
    	Optional<Appointment> appointment = Optional.of(appointment1);

    	assertDoesNotThrow(() -> controller.getAppointment(token, id));
    	resp = controller.getAppointment(token, id);
    	
    	assertNotNull(resp);
    	assertEquals(id,resp.getId());
    	
    }

	/*
    @Test
	@Order(4)
    void testputAppointment() {
    	assertThrows(IllegalStateException.class, () -> controller.putAppointment(token, null));

    	AppointmentDTO appointmentDTO = new AppointmentDTO(id, "01303983", false, "Best appointment ever", new User());
    	assertThrows(IllegalStateException.class, () -> controller.putAppointment(token, appointmentDTO));

		AppointmentDTO appointmentDTO2 = new AppointmentDTO(id, "01303983", false, "Best appointment ever", new User());
		assertThrows(IllegalStateException.class, () -> controller.putAppointment(token, appointmentDTO2));

		AppointmentDTO appointmentDTO3 =new AppointmentDTO();
		appointmentDTO3.setId(id);

    	assertDoesNotThrow(() -> controller.putAppointment(token, appointmentDTO3));
    }
    
    @Test
	@Order(5)
    void testdeleteAppointment() {
    	assertDoesNotThrow(() -> controller.deleteAppointment(token, id));
    }
    
	 */
}
