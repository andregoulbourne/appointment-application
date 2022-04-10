package com.appointments.test.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.appointments.models.Appointment;
import com.appointments.repo.IAppointment;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql({"/SCHEMA.sql","/DATA.sql"})
class IAppointmentTest {
	
	@Autowired
    IAppointment dao;
	
//	private final static List<String> CLEANUP_SCRIPTS = new ArrayList<String>() {
//		{
//			CLEANUP_SCRIPTS.add("classpath:DROP.sql");
//		}
//	};
	
//	@AfterEach
//	public void destroy() {
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2);
//		IntStream.range(0,CLEANUP_SCRIPTS.size()).forEach(index -> builder.addScript(CLEANUP_SCRIPTS.get(index)));
//		builder.build();
//	}
	
//	public void buildData() {
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2);
//		List<String> DATA_CREATION_SCRIPTS = new ArrayList<String>(List.of("classpath:SCHEMA.sql","classpath:DATA.sql"));
//		IntStream.range(0,DATA_CREATION_SCRIPTS.size()).forEach(index -> builder.addScript(DATA_CREATION_SCRIPTS.get(index)));
//		builder.build();
//	}
//	
//	@Before
//	public void setup() {
//		buildData();
//	}
	
	@Test
	void testFindByDate() {
		Appointment a= dao.findByScheduled("SA");
		assertNotNull(a);
	}

}
