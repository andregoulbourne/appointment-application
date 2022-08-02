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

import com.appointments.controller.AppointmentController;
import com.appointments.dao.IAppointment;
import com.appointments.dao.IUser;
import com.appointments.model.Appointment;
import com.appointments.model.AppointmentDTO;
import com.appointments.model.User;
import com.appointments.service.AppointmentService;

@SpringBootTest
class AppointmentsControllerTest {

	@Autowired
	AppointmentController controller;
	
    IUser urepo;
    
    IAppointment repo;
    
    AppointmentService service;
    
    @BeforeEach
    void setup() {
    	urepo = Mockito.mock(IUser.class);
    	repo = Mockito.mock(IAppointment.class);
    	service = Mockito.mock(AppointmentService.class);
    }
    
    //Using this number assuming it an unused id
    private int id = 99;
    
    @Test
    void testpostAppointment() {
    	AppointmentDTO appointmentDto = new AppointmentDTO();
    	Appointment appointment = new Appointment();
    	appointment.setId(id);
    	Mockito.when(service.appointmentDtoToAppointment(appointmentDto)).thenReturn(appointment);
    	Mockito.when(repo.save(appointment)).thenReturn(appointment);
    	
    	ReflectionTestUtils.setField(controller, "urepo", urepo);
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	ReflectionTestUtils.setField(controller, "service", service);
    	
    	assertDoesNotThrow(() -> controller.postAppointment(appointmentDto));
    	Appointment resp = controller.postAppointment(appointmentDto);
    	
    	assertNotNull(resp);
    	assertEquals(id,resp.getId());
    }
    
    @Test
    void testgetAll() {
    	List<Appointment> appointments = new ArrayList<>();
    	Appointment appointment = new Appointment();
    	appointments.add(appointment);
    	
    	Mockito.when(repo.findAll()).thenReturn(appointments);
    	
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	
    	assertDoesNotThrow(() -> controller.getAll());
    	List<Appointment> resp = controller.getAll();
    	
    	assertNotNull(resp);
    	assertEquals(1,resp.size());
    	assertEquals(appointment,resp.get(0));
    }
    
    @Test
    void testgetAppointment() {
    	assertDoesNotThrow(() -> controller.getAppointment(0));
    	Appointment resp = controller.getAppointment(0);
    	assertNull(resp);
    	
    	Appointment appointment1 =new Appointment();
    	appointment1.setId(id);
    	Optional<Appointment> appointment = Optional.of(appointment1);
    	
    	Mockito.when(repo.findById(id)).thenReturn(appointment);
    	
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	
    	assertDoesNotThrow(() -> controller.getAppointment(id));
    	resp = controller.getAppointment(id);
    	
    	assertNotNull(resp);
    	assertEquals(id,resp.getId());
    	
    }
    
    @Test
    void testputAppointment() {
    	assertDoesNotThrow(() -> controller.putAppointment(null, 0));
    	Appointment resp =controller.putAppointment(null, 0);
    	assertNull(resp);
    	
    	User user1 =new User();
    	user1.setId(1);
    	
    	Mockito.when(urepo.getById(id)).thenReturn(user1);
    	ReflectionTestUtils.setField(controller, "urepo", urepo);
    	
    	assertDoesNotThrow(() -> controller.putAppointment(null, 1));
    	Appointment resp1 =controller.putAppointment(null, 1);
    	assertNull(resp1);
    	
    	AppointmentDTO appointmentDTO = new AppointmentDTO(id, "01303983", false, "Best appointment ever", new User());
    	
    	assertDoesNotThrow(() -> controller.putAppointment(appointmentDTO,1));
    	
    	Appointment appointment1 =new Appointment();
    	appointment1.setId(id);
    	Optional<Appointment> appointment = Optional.of(appointment1);
    	
    	Mockito.when(repo.findById(id)).thenReturn(appointment);
    	
    	ReflectionTestUtils.setField(controller, "repo", repo);
    	
    	assertDoesNotThrow(() -> controller.putAppointment(appointmentDTO,1));
    }
    
    @Test
    void testdeleteAppointment() {
    	assertDoesNotThrow(() -> controller.deleteAppointment(id));
    }
}
