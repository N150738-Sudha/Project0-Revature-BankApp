package com.revature.pms.model;

import java.io.Serializable;

public class Employee implements Serializable {
	// fields of customer class
	private int employerUserId;
	private String employerPassword;
	private String employerName;
	private String employerEmail;
	private int employerContactNumber;
	private int employerBalance;
	private String employerAddress;
	
	// Default constructor
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	//Constructor for login
	public Employee(int employerUserId, String employerPassword) {
		super();
		this.employerUserId = employerUserId;
		this.employerPassword = employerPassword;
	}

	// Parameterized constructor
	public Employee(int employerUserId, String employerPassword, String employerName, String employerEmail,
			int employerContactNumber, int employerBalance, String employerAddress) {
		super();
		this.employerUserId = employerUserId;
		this.employerPassword = employerPassword;
		this.employerName = employerName;
		this.employerEmail = employerEmail;
		this.employerContactNumber = employerContactNumber;
		this.employerBalance = employerBalance;
		this.employerAddress = employerAddress;
	}

	// Generating getters and setters
	public int getEmployerUserId() {
		return employerUserId;
	}

	public void setEmployerUserId(int employerUserId) {
		this.employerUserId = employerUserId;
	}

	public String getEmployerPassword() {
		return employerPassword;
	}

	public void setEmployerPassword(String employerPassword) {
		this.employerPassword = employerPassword;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmployerEmail() {
		return employerEmail;
	}

	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}

	public int getEmployerContactNumber() {
		return employerContactNumber;
	}

	public void setEmployerContactNumber(int employerContactNumber) {
		this.employerContactNumber = employerContactNumber;
	}

	public int getEmployerBalance() {
		return employerBalance;
	}

	public void setEmployerBalance(int employerBalance) {
		this.employerBalance = employerBalance;
	}

	public String getEmployerAddress() {
		return employerAddress;
	}

	public void setEmployerAddress(String employerAddress) {
		this.employerAddress = employerAddress;
	}

	@Override
	public String toString() {
		return "Employee [employerUserId=" + employerUserId + ", employerName=" + employerName + ", employerEmail="
				+ employerEmail + ", employerContactNumber=" + employerContactNumber + ", employerBalance="
				+ employerBalance + ", employerAddress=" + employerAddress + "]";
	}
	
}
