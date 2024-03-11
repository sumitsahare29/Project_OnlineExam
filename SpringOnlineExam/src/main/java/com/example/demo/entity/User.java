package com.example.demo.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	public String username;
	public String password;
	public String mobno;
	public String email;
	
	
	
	
	public User() {
		super();
	}



	public User(String username, String password, String mobno, String email) {
		super();
		this.username = username;
		this.password = password;
		this.mobno = mobno;
		this.email = email;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getMobno() {
		return mobno;
	}



	public void setMobno(String mobno) {
		this.mobno = mobno;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", mobno=" + mobno + ", email=" + email + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}



		
	
	
	
	
	
	
}
