package com.cirb.archive.domain;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String email;

	private String firstName;

	private String lastName;
	
	private String login;
	
	private String password;

	public User() {
		super();
	}

	public User(String email, String firstName, String lastName, String login, String password) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
