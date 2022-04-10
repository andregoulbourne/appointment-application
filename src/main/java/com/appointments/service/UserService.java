package com.appointments.service;

import org.springframework.stereotype.Service;

import com.appointments.dto.UserDTO;
import com.appointments.models.User;

@Service
public class UserService {
	public User userDtoToUser(UserDTO  userDto) {
		return new User(userDto.getUsername(), userDto.getPwd(), 
				userDto.getFirstName(), userDto.getLastName(),
				userDto.getMiddleName(),userDto.getAppointments(), userDto.getPhone());
	}
}
