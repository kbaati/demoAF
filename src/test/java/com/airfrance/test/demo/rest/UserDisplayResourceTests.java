package com.airfrance.test.demo.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.airfrance.demo.DemoApplication;
import com.airfrance.demo.exceptions.ResourceNotFoundException;
import com.airfrance.test.demo.AbstractTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class UserDisplayResourceTests extends AbstractTest {

	@Autowired
	private MockMvc mvc;

	/**
	 * find a user by Id
	 * @throws Exception
	 */
	@Test
	public void displayUserById() throws Exception {
		String uri = "/display/users/61e5facbd586b144aa6f52cd";

		mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is("khaled.baati@gmail.com")))
				.andExpect(jsonPath("$.country", is("France"))).andExpect(jsonPath("$.firstName", is("khaled")))
				.andExpect(jsonPath("$.lastName", is("baati"))).andExpect(jsonPath("$.age", is(35)));
	}

	/**
	 * Find a user by Email
	 * 
	 * @throws Exception
	 */
	@Test
	public void displayUserByEmail() throws Exception {
		String uri = "/display/users?email=khaled.baati@gmail.com";

		mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("61e5facbd586b144aa6f52cd")))
				.andExpect(jsonPath("$.country", is("France"))).andExpect(jsonPath("$.firstName", is("khaled")))
				.andExpect(jsonPath("$.lastName", is("baati"))).andExpect(jsonPath("$.age", is(35)));
	}

	/**
	 * try to find a user by a fake Id
	 * @throws Exception
	 */
	@Test
	public void displayUserByFakeId() throws Exception {
		String uri = "/display/users/44444444444444";

		mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
				.andExpect(result -> assertEquals("User not found for this id: 44444444444444",
						result.getResolvedException().getMessage()));
	}

	/**
	 * 	try to find a user by a fake email
	 * @throws Exception
	 */
	@Test
	public void displayUserByFakeEmail() throws Exception {
		String uri = "/display/users?email=blabla@gmail.com";

		mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
				.andExpect(result -> assertEquals("User not found for this email: blabla@gmail.com",
						result.getResolvedException().getMessage()));
	}
}
