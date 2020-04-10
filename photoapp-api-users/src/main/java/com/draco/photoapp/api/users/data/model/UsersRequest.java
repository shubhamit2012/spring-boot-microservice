package com.draco.photoapp.api.users.data.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsersRequest {

	@NotNull(message = "First name cannot be empty")
	@Size(min = 2, message = "First name cannot be less that 2 characters")
	public String firstName;

	@NotNull(message = "Last name cannot be empty")
	@Size(min = 2, message = "Last name cannot be less that 2 characters")
	public String lastName;

	@NotNull(message = "Email cannot be empty")
	@Size(min = 2, message = "Email cannot be less that 2 characters")
	@Email
	public String email;

	@NotNull(message = "Password cannot be empty")
	@Size(min = 2, message = "Password name cannot be less that 2 characters")
	public String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
