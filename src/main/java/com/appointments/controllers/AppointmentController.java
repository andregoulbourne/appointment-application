package com.appointments.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointments.dto.AppointmentDTO;
import com.appointments.models.Appointment;
import com.appointments.models.User;
import com.appointments.repo.IAppointment;
import com.appointments.repo.IUser;
import com.appointments.service.AppointmentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("appointments")
public class AppointmentController {
	
    @Autowired
    IAppointment repo;
    
    @Autowired
    IUser urepo;
    
    @Autowired
	AppointmentService service;
    
    private static Logger logger = LogManager.getLogger(AppointmentController.class);
    
    /**
     * Creates a new Appointment in the database
     * @param Appointment new Appointment being created
     * @return the representation of the Appointment with its newly generated primary key.
     */
    @PostMapping
    public Appointment postAppointment(@RequestBody AppointmentDTO appointmentDto) {
        return repo.save(service.appointmentDtoToAppointment(appointmentDto));
    }


    /**
     * Retrieves all Appointment stored in the database
     * @return List of all Appointment in the database in JSON format
     */
    @GetMapping
    public List<Appointment> getAll() {
        return repo.findAll();
    }
   
    /**
     * Retrieves an Appointment based on the given ID
     * @param id id of the Appointment
     * @return Single Appointment found
     */
    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable(name = "id") int id) {
    	Optional<Appointment> appointment= repo.findById(id); 
        if(appointment.isPresent()) return appointment.get();
        return null;
    }

 /**
	 * 
	 * @param id     of already existing user
	 * @param appointment with changes to update
	 * @return the newly changed appointment
	 */
	@PutMapping("/{id}")
	public Appointment putAppointment (@RequestBody AppointmentDTO appointmentDTO, @PathVariable(name = "id") int id) {
		User user;
		Appointment appointment;
		try {
			user = urepo.getById(id);
			appointment = new Appointment(appointmentDTO.getId(), appointmentDTO.getDate(), appointmentDTO.isPassed(), appointmentDTO.getDescription(), user);
		} catch (NullPointerException e) {
			logger.info("Exception occured updating appointment ...");
			return null;
		}
		Optional<Appointment> update = repo.findById(appointment.getId());
		if (update.isPresent()) {
			Appointment newAppointment = update.get();
			repo.saveAndFlush(newAppointment);
		} else {
			appointment = repo.save(appointment);
		}
		return appointment;
	}
    /**
     * Deletes the associated appointment
     * @param AppointmentId ID of the about me being deleted
     */
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable(name = "id") int appointmentId) {
        repo.deleteById(appointmentId);
    }

}
