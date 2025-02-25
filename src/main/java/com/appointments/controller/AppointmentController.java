package com.appointments.controller;

import java.util.List;
import java.util.Optional;

import com.appointments.service.SessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.appointments.dao.IAppointment;
import com.appointments.dao.IUser;
import com.appointments.model.Appointment;
import com.appointments.model.AppointmentDTO;
import com.appointments.model.User;
import com.appointments.service.AppointmentService;

@RestController
@RequestMapping("appointments")
public class AppointmentController {
    private static final Logger logger = LogManager.getLogger(AppointmentController.class);

    @Autowired
    public AppointmentController(IAppointment appointmentRepository, IUser userRepository, AppointmentService appointmentService, SessionService sessionService){
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.appointmentService = appointmentService;
        this.sessionService = sessionService;
    }

    IAppointment appointmentRepository;

    IUser userRepository;

	AppointmentService appointmentService;

    SessionService sessionService;
    
    /**
     * Creates a new Appointment in the database
     * @param Appointment new Appointment being created
     * @return the representation of the Appointment with its newly generated primary key.
     */
    @PostMapping
    public Appointment postAppointment(@CookieValue("tokenAppointmentsApp") String token, @RequestBody AppointmentDTO appointmentDto) {
        return appointmentRepository.save(appointmentService.appointmentDtoToAppointment(appointmentDto));
    }


    /**
     * Retrieves all Appointment stored in the database
     * @return List of all Appointment in the database in JSON format
     */
    @GetMapping
    public List<Appointment> getAll(@CookieValue("tokenAppointmentsApp") String token) {
        return appointmentRepository.findAll();
    }

    /**
     * Retrieves an Appointment based on the given ID
     * @param id id of the Appointment
     * @return Single Appointment found
     */
    @GetMapping("/{id}")
    public Appointment getAppointment(@CookieValue("tokenAppointmentsApp") String token, @PathVariable(name = "id") int id) {
    	Optional<Appointment> appointment= appointmentRepository.findById(id);
        return appointment.orElse(null);
    }

 /**
	 * 
	 * @param id     of already existing user
	 * @param appointment with changes to update
	 * @return the newly changed appointment
	 */
	@PutMapping("/{id}")
	public Appointment putAppointment (@CookieValue("tokenAppointmentsApp") String token, @RequestBody AppointmentDTO appointmentDTO) {
		User user;
		Appointment appointment;
		try {
			user = userRepository.getById(sessionService.getSession(token).getUser().getId());
			appointment = new Appointment(appointmentDTO.getId(), appointmentDTO.getDate(), appointmentDTO.isPassed(), appointmentDTO.getDescription(), user);
		} catch (NullPointerException e) {
			logger.info("Exception occured updating appointment ...");
			throw new IllegalStateException();
		}

        if(!appointmentService.isAppointmentExistingForUser(user, appointmentDTO.getId())){
            logger.info("Appointment does not exist for this user ...");
            throw new IllegalStateException();
        }

		Optional<Appointment> update = appointmentRepository.findById(appointment.getId());
		if (update.isPresent()) {
			Appointment newAppointment = update.get();
			appointmentRepository.saveAndFlush(newAppointment);
		} else {
			appointment = appointmentRepository.save(appointment);
		}
		return appointment;
	}
    /**
     * Deletes the associated appointment
     * @param AppointmentId ID of the about me being deleted
     */
    @DeleteMapping("/{id}")
    public void deleteAppointment(@CookieValue("tokenAppointmentsApp") String token, @PathVariable(name = "id") int appointmentId) {
        User user = userRepository.getById(sessionService.getSession(token).getUser().getId());

        if(!appointmentService.isAppointmentExistingForUser(user, appointmentId)){
            logger.info("Appointment does not exist for this user ...");
            throw new IllegalStateException();
        }

        appointmentRepository.deleteById(appointmentId);
    }

}
