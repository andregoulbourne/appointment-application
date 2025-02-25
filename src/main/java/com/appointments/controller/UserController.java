package com.appointments.controller;

import java.util.List;
import java.util.Optional;

import com.appointments.model.Session;
import com.appointments.service.SessionService;
import com.appointments.util.EncryptionUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.appointments.dao.IUser;
import com.appointments.model.User;
import com.appointments.model.UserDTO;
import com.appointments.service.UserService;


@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(IUser userRepository, UserService userService, SessionService sessionService, EncryptionUtility encryptionUtility){
        this.userRepository = userRepository;
        this.userService = userService;
        this.encryptionUtility = encryptionUtility;
        this.sessionService = sessionService;
    }

    IUser userRepository;
    
    UserService userService;

    SessionService sessionService;

    EncryptionUtility encryptionUtility;
    
    /**
     * Creates a new User in the database
     * @param User new User being created
     * @return the representation of the User with its newly generated primary key.
     */
    @PostMapping
    public User postUser(@RequestBody UserDTO userDto) {
        User user = userRepository.findByEmailId(userDto.getEmailId());

        if(user != null)
            throw new IllegalStateException("User already exist");

        return userRepository.save(userService.userDtoToUser(userDto));
    }
    
    /**
     * Logs in a User based on the given email Id and pwd
     * @param  User
     * @return Single User found
     */
    @PostMapping("/login")
    public User getUser(@RequestBody UserDTO userDto) {
        User userRetrieved = userRepository.findByEmailId(userDto.getEmailId());
        if(StringUtils.equals(userRetrieved.getPwd(), userDto.getPwd())) {
        	logger.info("Login success ...");
            Session session = new Session();
            String token = encryptionUtility.encrypt(userDto.getEmailId());
            session.setToken(token);
            userRetrieved.setPwd(null);
            session.setUser(userRetrieved);
            sessionService.addSessionToCache(session);
        	return userRetrieved;
        }
        else return null;
    }

}