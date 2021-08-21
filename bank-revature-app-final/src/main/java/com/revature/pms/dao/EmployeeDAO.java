package com.revature.pms.dao;

import java.util.List;

import com.revature.pms.model.Customer;
import com.revature.pms.model.Employee;

public interface EmployeeDAO {
	public boolean creatAccount(Employee employer);
	public List<Customer> getAllCustomerDetails(int detailsType);
	public Customer getCustomerById(int customerUserId);
	public List<Customer> getCustomerByName(String customerName);
	public boolean acceptOrDecline(int customerUserId);
	public boolean login(int employerUserId, String Password);
	public boolean deleteCustomer(int customerUserId);

}

