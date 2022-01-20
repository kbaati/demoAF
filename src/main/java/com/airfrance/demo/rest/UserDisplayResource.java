package com.airfrance.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airfrance.demo.Constants;
import com.airfrance.demo.entity.User;
import com.airfrance.demo.exceptions.ResourceNotFoundException;
import com.airfrance.demo.service.UserService;


@RestController
@RequestMapping("/display")
public class UserDisplayResource {

	private final Logger log = LoggerFactory.getLogger(UserDisplayResource.class);
	@Autowired
	private final UserService userService;


	public UserDisplayResource(UserService userService) {
		this.userService = userService;
	}

	/**
	 * {@code GET /users/:id}
	 *
	 * @param id the id of the user to find.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the "id" user, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		log.debug("REST request to get User : {}", id);

		User user = userService.getUserById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND_ID + id));
		return ResponseEntity.ok().body(user);
	}
	
	/**
	 * {@code GET /users?email}.
	 *
	 * @param email the email of the user to find.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the "email" user, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/users")
	public ResponseEntity<User> getUserByEmail(@RequestParam(value="email")  String email) {
		log.debug("REST request to get User by email: {}", email);

		User user = userService.getUserByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND_EMAIL + email));
		return ResponseEntity.ok().body(user);
	}
	


}
