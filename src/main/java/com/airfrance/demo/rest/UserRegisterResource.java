package com.airfrance.demo.rest;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airfrance.demo.Constants;
import com.airfrance.demo.dto.UserDTO;
import com.airfrance.demo.entity.User;
import com.airfrance.demo.repository.UserRepository;
import com.airfrance.demo.service.UserService;

@RestController
@RequestMapping("/register")
public class UserRegisterResource {

	private final Logger log = LoggerFactory.getLogger(UserRegisterResource.class);

	@Autowired
	private UserService userService;
	@Autowired

	private UserRepository userRepository;

	public UserRegisterResource() {

	}

	public UserRegisterResource(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	/**
	 * {@code POST  /users} : Creates a new user.
	 * <p>
	 * Creates a new user if the email are not already used.
	 * 
	 * @param userDTO the user to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new user, or with status {@code 400 (Bad Request)} if the
	 *         email is already in use.
	 * @throws URISyntaxException 
	 */

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException  {
		log.debug("REST request to save User : {}", userDTO);
		if (userDTO.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ID_GIVEN);
			
			// Lowercase the user login before comparing with database
		} else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.EMAIL_EXIST);

		} else if (userDTO.getAge() < 18) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Constants.NOT_ALLOWED_AGE);

		} else if (!userDTO.getCountry().equalsIgnoreCase(Constants.FRANCE_COUNTRY)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Constants.NOT_ALLOWED_COUNTRY);

		} else {
			User newUser = userService.registerUser(userDTO);
			//return ResponseEntity.created(new URI("/register/users/" + newUser.getFirstName())).body(newUser);
			return new ResponseEntity<User>(newUser,HttpStatus.OK);

		}
	}
//
//	@GetMapping("/users/test")
//	public ResponseEntity<?> createUserTest() {
//		return new ResponseEntity<>("{\"01\":\" test it \"}", HttpStatus.BAD_REQUEST);
//
//	}

}
