package com.appointments.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointments.DTO.UserDTO;
import com.appointments.models.User;
import com.appointments.repo.IUser;


@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("users")
public class UserController {
	
    @Autowired
    IUser repo;
    
    /**
     * Creates a new User in the database
     * @param User new User being created
     * @return the representation of the User with its newly generated primary key.
     */
    @PostMapping
    public User postUser(@RequestBody User user) {
        return repo.save(user);
    }


    /**
     * Retrieves all User stored in the database
     * @return List of all User in the database in JSON format
     */
    @GetMapping
    public List<User> getAll() {
        List<User> users = repo.findAll();
        return users;
    }
   
    /**
     * Retrieves an User based on the given ID
     * @param id id of the User
     * @return Single User found
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") int id) {
        return repo.findById(id).get();
    }
    
    /**
     * Retrieves an User based on the given ID
     * @param username of the User
     * @return Single User found
     */
    @PostMapping("/login")
    public User getUser(@RequestBody User user) {
        User userRetrieved = repo.findByEmailId(user.getEmailId());
        if(userRetrieved.getPwd().equals(user.getPwd())) return userRetrieved;
        else return new User();
    }
    
	/**
	 * 
	 * @param id     of already existing user
	 * @param user with changes to update
	 * @return the newly changed user
	 */
	@PutMapping
	public User putUser(@RequestBody UserDTO userDTO) {
		User user;
		try {
			user = new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPwd(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getMiddleName(), userDTO.getEmailId(), userDTO.getAppointments()
					, userDTO.getPhone(), userDTO.isAdmin(), userDTO.isVendor());
		} catch (NullPointerException e) {
			user = new User();
			user.setId(userDTO.getId());
		}
		Optional<User> update = repo.findById(user.getId());
		if (update.isPresent()) {
			User newUser = update.get();
			repo.saveAndFlush(newUser);
		} else {
			user = repo.save(user);
		}
		return user;
	}


    /**
     * Deletes the associated user
     * @param UserId ID of the about me being deleted
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") int userId) {
        repo.deleteById(userId);
    }

}