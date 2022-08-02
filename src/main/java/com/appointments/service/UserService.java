package com.appointments.service;

import org.springframework.stereotype.Service;

import com.appointments.model.User;
import com.appointments.model.UserDTO;

@Service
public class UserService {
	public User userDtoToUser(UserDTO  userDto) {
		return new User(userDto.getUsername(), userDto.getPwd(), 
				userDto.getFirstName(), userDto.getLastName(),
				userDto.getMiddleName(),userDto.getAppointments(), userDto.getPhone());
	}
}
