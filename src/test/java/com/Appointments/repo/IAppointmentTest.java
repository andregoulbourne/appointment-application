package com.Appointments.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.Appointments.models.Appointment;

@DataJpaTest
public class IAppointmentTest {
    private Appointment appointment;
     
    @Autowired
    private IAppointment repo;
     
    @Test
    @Rollback(false)
    public void testSaveNewAppointment() {
    }
     
    @Test  
    public void testUpdateAppointment() {
    }
     
    @Test  
    public void testDeleteAppointment() {
    }
    @Test  
    public void testReadAppointment() {
    }
    @Test  
    public void testReadAllAppointment() {
    }
}
