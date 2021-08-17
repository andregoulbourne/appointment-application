package com.Appointments.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Appointments.models.Appointment;
import com.Appointments.models.User;

@Repository
public interface IAppointment extends JpaRepository<Appointment, Integer>{
	public <Optional>Appointment findByDate(String date);
}
