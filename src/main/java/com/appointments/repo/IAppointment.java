package com.appointments.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointments.models.Appointment;

@Repository
public interface IAppointment extends JpaRepository<Appointment, Integer>{
	public <Optional>Appointment findByDate(String date);
}
