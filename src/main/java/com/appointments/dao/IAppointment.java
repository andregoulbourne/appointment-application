package com.appointments.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointments.model.Appointment;

@Repository
public interface IAppointment extends JpaRepository<Appointment, Integer>{
	public Appointment findByScheduled(String date);
}
