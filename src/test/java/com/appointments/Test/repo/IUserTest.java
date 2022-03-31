package com.appointments.Test.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.appointments.models.User;
import com.appointments.repo.IUser;

@RunWith(SpringRunner.class)
@DataJpaTest
class IUserTest {

	@Autowired
	private IUser repo;

	private User user;

	private User user2;

	@BeforeEach
	void setup() {
		this.user = new User("patient", "password", "firstName", "lastName", "middleName", new ArrayList<>(),
				"19735678888");
		this.user2 = new User("patient2", "password2", "firstName2", "lastName2", "middleName2", new ArrayList<>(),
				"19735678882");
		repo.save(user2);
		repo.save(new User("patient3", "password3", "firstName3", "lastName3", "middleName3", new ArrayList<>(),
				"19735678885"));
	}

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(repo).isNotNull();
	}

	@Test
	void testSaveNewUser() {
		User savedUser = repo.save(user);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	void testReadAllUser() {
		repo.save(user);
		List<User> users = repo.findAll();
		assertNotNull(users);
		assertEquals(users.size(), 3);
	}

	@Test
	void testFindAUser() {
		User foundUser = repo.findByUsername("patient3");
		assertEquals("patient3", foundUser.getUsername());
		assertEquals("firstName3", foundUser.getFirstName()); 
	}

	@Test
	void testfindUserAndUpdateUser() {
		User user = repo.findByUsername("patient3");
		user.setUsername("Hello");
		repo.save(user);
		User updatedUser = repo.findByUsername("Hello");
		assertNotNull(updatedUser);
		assertEquals("firstName3", user.getFirstName());
	}

	@Test
	void testDeleteUser() {
		repo.delete(user2);
		User user2 = repo.findByUsername("patient2");
		assertNull(user2);
	}

}
