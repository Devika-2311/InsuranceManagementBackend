package com.example.model;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String phoneNo;
	private String emailId;
	private String address;
	 @NotNull
	 @Column(unique=true)
	private String password;
	// @Column(columnDefinition = "VARCHAR(255) default 'user'")
	 private String role="user";
	private LocalDate createdAt;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", phoneNo=" + phoneNo + ", emailId=" + emailId + ", address=" + address + ", password=" + password
				+ ", role=" + role + ", createdAt=" + createdAt + "]";
	}
	public User(Long userId, String firstName, String lastName, LocalDate dob, String phoneNo, String emailId, String address,
			String password, String role, LocalDate createdAt) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.address = address;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
	}
	public User() {
		super();
		
	}
	
}
