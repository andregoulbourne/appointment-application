package com.appointments.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointments.appointment.Appointment;

@Repository
public interface IAppointment extends JpaRepository<Appointment, Integer>{
	public Appointment findByScheduled(String date);
}
