package com.airfrance.test.demo.rest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.airfrance.demo.DemoApplication;
import com.airfrance.demo.entity.User;
import com.airfrance.test.demo.AbstractTest;

import net.bytebuddy.utility.RandomString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class UserRegisterResourceTests extends AbstractTest {

	@Autowired
	private MockMvc mvc;

	/**
	 * create a new correct user
	 * @throws Exception
	 */
	@Test
	public void creatUser() throws Exception {

		String uri = "/register/users";
		User user = new User();
		user.setAge(54);
		user.setCountry("France");
		//generate a new email
		String generatedStringEmail = RandomString.make(5).concat("@gmail.com");
		user.setEmail(generatedStringEmail);

		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		// String content = mvcResult.getResponse().getContentAsString();
		// assertEquals(content,user );
	}
	
	/**
	 * try to create a user with existing email
	 * @throws Exception
	 */
	@Test
	public void creatUserWithExistingEmail() throws Exception {
		String uri = "/register/users";
		User user = new User();
		user.setAge(35);
		user.setCountry("France");
		user.setEmail("khaled.baati@gmail.com");

		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Email are already used");
	}

	/**
	 * try to create a user under 18years
	 * @throws Exception
	 */
	@Test
	public void creatUserUnder18() throws Exception {

		String uri = "/register/users";
		User user = new User();
		user.setAge(17);
		user.setCountry("France");
		String generatedStringEmail = RandomString.make(5).concat("@gmail.com");
		user.setEmail(generatedStringEmail);

		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "You can't create a user,you must have more than 18 years");
	}

	/**
	 * try to create a user didn't live in France
	 * @throws Exception
	 */
	@Test
	public void creatUserLiveHorsFrance() throws Exception {

		String uri = "/register/users";
		User user = new User();
		user.setAge(54);
		user.setCountry("Italy");
		String generatedStringEmail = RandomString.make(5).concat("@gmail.com");
		user.setEmail(generatedStringEmail);

		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "You can't create a user, you must live in France");
	}

}
