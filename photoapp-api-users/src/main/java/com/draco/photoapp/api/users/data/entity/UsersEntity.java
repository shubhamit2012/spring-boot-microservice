package com.draco.photoapp.api.users.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersEntity implements Serializable {

	private static final long serialVersionUID = 18564086150840191L;

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "firstName", nullable=false, length=50)
	private String firstName;

	@Column(name = "lastName", nullable=false, length=50)
	private String lastName;

	@Column(name = "email", nullable=false, length=120, unique=true)
	private String email;

	@Column(name = "userId", nullable=false, unique=true)
	private String userId;

	@Column(name = "encryptedPassword", nullable=false, unique=true)
	private String encryptedPassword;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

}
