package com.Appointments.controllers;

import java.util.List;
import java.util.Optional;

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

import com.Appointments.DTO.AppointmentDTO;
import com.Appointments.models.Appointment;
import com.Appointments.models.User;
import com.Appointments.repo.IAppointment;
import com.Appointments.repo.IUser;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("appointments")
public class AppointmentController {
	
    @Autowired
    IAppointment repo;
    
    @Autowired
    IUser urepo;
    
    /**
     * Creates a new Appointment in the database
     * @param Appointment new Appointment being created
     * @return the representation of the Appointment with its newly generated primary key.
     */
    @PostMapping
    public Appointment postAppointment(@RequestBody Appointment appointment) {
        return repo.save(appointment);
    }


    /**
     * Retrieves all Appointment stored in the database
     * @return List of all Appointment in the database in JSON format
     */
    @GetMapping
    public List<Appointment> getAll() {
        List<Appointment> appointments = repo.findAll();
        return appointments;
    }
   
    /**
     * Retrieves an Appointment based on the given ID
     * @param id id of the Appointment
     * @return Single Appointment found
     */
    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable(name = "id") int id) {
        return repo.findById(id).get();
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
		try {
			user = urepo.getById(id);
		} catch (NullPointerException e) {
			System.out.println("Sorry that user doesn't exist");
			return null;
		}
		Appointment appointment;
		try {
			appointment = new Appointment(appointmentDTO.getId(), appointmentDTO.getDate(), appointmentDTO.isPassed(), appointmentDTO.getDescription(), user);
		} catch (NullPointerException e) {
			appointment = new Appointment();
			appointment.setId(appointmentDTO.getId());
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
