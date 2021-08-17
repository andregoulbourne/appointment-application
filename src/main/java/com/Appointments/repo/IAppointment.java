package com.Appointments.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Appointments.models.Appointment;

@Repository
public interface IAppointment extends JpaRepository<Appointment, Integer>{

}
