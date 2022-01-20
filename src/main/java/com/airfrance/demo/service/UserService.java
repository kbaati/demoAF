package com.airfrance.demo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airfrance.demo.dto.UserDTO;
import com.airfrance.demo.entity.User;
import com.airfrance.demo.repository.UserRepository;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private  UserRepository userRepository;
	
	public UserService() {

	}
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	public User registerUser(@Valid UserDTO userDTO) {
//		userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
//			throw new EmailAlreadyUsedException();
//		});

		User newUser = new User();
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		if (userDTO.getEmail() != null) {
			newUser.setEmail(userDTO.getEmail().toLowerCase());
		}

		newUser.setAge(userDTO.getAge());
		newUser.setCountry(userDTO.getCountry());
		userRepository.save(newUser);
		log.debug("Created Information for User: {}", newUser);
		return newUser;
	}

	public Optional<User> getUserById(String id) {
		return userRepository.findById(id);

	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmailIgnoreCase(email);
	}

}
