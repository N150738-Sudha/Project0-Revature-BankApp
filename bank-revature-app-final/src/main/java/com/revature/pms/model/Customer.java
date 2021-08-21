package com.revature.pms.model;

import java.io.Serializable;

public class Customer implements Serializable {
	// fields of customer class
	private int customerUserId;
	private String customerPassword;
	private String customerName;
	private String customerEmail;
	private int customerContactNumber;
	private int customerBalance;
	private String customerAddress;
	private String customerStatus;

	// Default constructor
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	//Costructor for getting details of customer by employee
	public Customer(int customerUserId, String customerName, String customerEmail,
			int customerContactNumber, int customerBalance, String customerAddress, String customerStatus) {
		super();
		this.customerUserId = customerUserId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContactNumber = customerContactNumber;
		this.customerBalance = customerBalance;
		this.customerAddress = customerAddress;
	}
	
	// Parameterized constructor
	public Customer(int customerUserId, String customerPassword, String customerName, String customerEmail,
			int customerContactNumber, int customerBalance, String customerAddress, String customerStatus) {
		super();
		this.customerUserId = customerUserId;
		this.customerPassword = customerPassword;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContactNumber = customerContactNumber;
		this.customerBalance = customerBalance;
		this.customerAddress = customerAddress;
	}
	
	
	//Constructor with parameters customerUserId and customerPassword for login purpose
	public Customer(int customerUserId, String customerPassword) {
		super();
		this.customerUserId = customerUserId;
		this.customerPassword = customerPassword;
	}

	// Generating getters and setters
	public int getCustomerUserId() {
		return customerUserId;
	}

	public void setCustomerUserId(int customerUserId) {
		this.customerUserId = customerUserId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(int customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public int getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	

	public String getCustomerStatus() {
		return customerStatus;
	}


	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	@Override
	public String toString() {
		return "\nCustomer [customerUserId=" + customerUserId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerContactNumber=" + customerContactNumber + ", customerBalance="
				+ customerBalance + ", customerAddress=" + customerAddress + ", customerStatus=" + customerStatus +"]";
	}

}
