package com.airfrance.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@org.springframework.data.mongodb.core.mapping.Document(collection = "user")
public class User {
	/*
	 * the id field
	 */
	@Id()
	@GeneratedValue 
	private String id;    
	/*
	 * the firstName field
	 */
    @Size(max = 50)
    @Field("first_name")
    private String firstName;
    /*
	 * the lastName field
	 */
    @Size(max = 50)
    @Field("last_name")
    private String lastName;    
    /*
	 * the age field
	 */
    @Field("age")
    @NotNull(message = "age cannot be null")
    private Integer age;    
    /*
	 * the country field
	 */
    @Field("country")
    @NotNull(message = "country cannot be null")
    private String country;    
    /*
	 * the email field
	 */
    @Email
    @Field("email")
    @NotNull(message = "email cannot be null")
    private String email;

    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", country="
				+ country + ", email=" + email + "]";
	}
    
}
