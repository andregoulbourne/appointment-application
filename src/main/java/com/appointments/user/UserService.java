package com.appointments.user;

import org.springframework.stereotype.Service;

import com.appointments.user.UserDTO;
import com.appointments.user.User;

@Service
public class UserService {
	public User userDtoToUser(UserDTO  userDto) {
		return new User(userDto.getUsername(), userDto.getPwd(), 
				userDto.getFirstName(), userDto.getLastName(),
				userDto.getMiddleName(),userDto.getAppointments(), userDto.getPhone());
	}
}
