package com.airfrance.demo.dto;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.airfrance.demo.entity.User;

/**
 * A DTO representing a user.
 */
public class UserDTO {
	
	@Id()
	@GeneratedValue  (strategy = GenerationType.IDENTITY)
	private String id;
    
    @Size(max = 50)
    @Field("first_name")
    private String firstName;

    @Size(max = 50)
    @Field("last_name")
    private String lastName;
    
    @Field("age")
    @NotNull(message = "age cannot be null")
    private Integer age;
    
    @Field("country")
    @NotNull(message = "country cannot be null")
    private String country;
    
    @Email
    @Field("email")
    @NotNull(message = "email cannot be null")
    private String email;

    
    public UserDTO() {
        // Empty constructor.
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.age=user.getAge();
        this.country=user.getCountry();
       
    }
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
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", country=" + country + ", email=" + email + "]";
	}
    
    
    
}
