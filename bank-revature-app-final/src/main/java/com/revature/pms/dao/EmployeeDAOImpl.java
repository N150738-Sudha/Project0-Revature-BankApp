package com.revature.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.pms.model.Customer;
import com.revature.pms.model.Employee;
import com.revature.pms.util.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {
	//Getting connection with the driver
	Connection connection = DBConnection.getDBConnection();
	
	//Writing SQL queries to execute
	private final String CREATE_ACCOUNT_QUERY = "insert into hr.employee values(?,?,?,?,?,?,?)";
	private final String GET_ALL_CUSTOMERS_DETAILS_QUERY = "select * from hr.customer";
	private final String GET_CUSTOMERS_BASED_ON_STATUS_QUERY = "select * from hr.customer where customerStatus = ?";
	private final String GET_CUSTOMER_BY_ID_QUERY = "select * from hr.customer where customerUserId = ?";
	private final String GET_CUSTOMER_BY_NAME_QUERY = "select * from hr.customer where customerName = ?";
	private final String LOGIN_QUERY = "select * from hr.employee where employerUserId = ? and employerPassword = ?";
	private final String GIVING_PERMISSION_QUERY = "update hr.customer set customerStatus = ? where customerUserId = ?";
	private final String DELETE_CUSTOMER_QUERY = "delete from hr.customer where customerUserId = ?";

	//function to create account by an employee
	public boolean creatAccount(Employee employer) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_ACCOUNT_QUERY);
			statement.setInt(1, employer.getEmployerUserId());
			statement.setString(2, employer.getEmployerPassword());
			statement.setString(3, employer.getEmployerName());
			statement.setString(4, employer.getEmployerEmail());
			statement.setInt(5, employer.getEmployerContactNumber());
			statement.setInt(6, employer.getEmployerBalance());
			statement.setString(7, employer.getEmployerAddress());
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;
	}

	//Obtaining all customer details
	public List<Customer> getAllCustomerDetails(int detailsType) {

		List<Customer> customers = new ArrayList<Customer>();

		try {
			Statement statement = connection.createStatement();
			PreparedStatement statement1 = null;
			ResultSet res = null;
			switch (detailsType) {
			
			//Getting details of customers whose status is in progress
			case 1:
				statement1 = connection.prepareStatement(GET_CUSTOMERS_BASED_ON_STATUS_QUERY);
				statement1.setString(1, "In progress");
				res = statement1.executeQuery();
				break;
				
			//Getting details of customers whose account is approved
			case 2:
				statement1 = connection.prepareStatement(GET_CUSTOMERS_BASED_ON_STATUS_QUERY);
				statement1.setString(1, "Approved");
				res = statement1.executeQuery();
				break;
				
			//Getting all customer details
			case 3:
				res = statement.executeQuery(GET_ALL_CUSTOMERS_DETAILS_QUERY);
				break;
			}
			while (res.next()) {
				Customer customer = new Customer();
				customer.setCustomerUserId(res.getInt(1));
				customer.setCustomerPassword(res.getString(2));
				customer.setCustomerName(res.getString(3));
				customer.setCustomerEmail(res.getString(4));
				customer.setCustomerContactNumber(res.getInt(5));
				customer.setCustomerBalance(res.getInt(6));
				customer.setCustomerAddress(res.getString(7));
				customer.setCustomerStatus(res.getString(8));
				// Getting customer details
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

	//Getting details of a customer by using customer Id
	public Customer getCustomerById(int customerUserId) {
		Customer customer = new Customer();

		PreparedStatement stat = null;
		try {
			stat = connection.prepareStatement(GET_CUSTOMER_BY_ID_QUERY);
			stat.setInt(1, customerUserId);
			ResultSet res = stat.executeQuery();
			res.next();

			customer.setCustomerUserId(res.getInt("customerUserId"));
			customer.setCustomerPassword(res.getString("customerPassword"));
			customer.setCustomerName(res.getString("customerName"));
			customer.setCustomerEmail(res.getString("customerEmail"));
			customer.setCustomerContactNumber(res.getInt("customerContactNumber"));
			customer.setCustomerBalance(res.getInt("customerBalance"));
			customer.setCustomerAddress(res.getString("customerAddress"));
			customer.setCustomerStatus(res.getString("customerStatus"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	//Getting customer details by using customer name
	public List<Customer> getCustomerByName(String customerName) {

		List<Customer> customers = new ArrayList<Customer>();
		PreparedStatement stat = null;
		try {
			stat = connection.prepareStatement(GET_CUSTOMER_BY_NAME_QUERY);
			stat.setString(1, customerName);
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				Customer customer = new Customer();
				customer.setCustomerUserId(res.getInt(1));
				customer.setCustomerPassword(res.getString(2));
				customer.setCustomerName(res.getString(3));
				customer.setCustomerEmail(res.getString(4));
				customer.setCustomerContactNumber(res.getInt(5));
				customer.setCustomerBalance(res.getInt(6));
				customer.setCustomerAddress(res.getString(7));
				customer.setCustomerStatus(res.getString(8));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

	//Giving permissions to customer accounts
	public boolean acceptOrDecline(int customerUserId) {
		int res = 0;

		try {
			PreparedStatement stat = connection.prepareStatement(GIVING_PERMISSION_QUERY);
			System.out.println("Do you want to accept or decline the account of " + customerUserId);
			System.out.println("select 1 to accept or 2 to decline");
			Scanner sp = new Scanner(System.in);
			int permission = sp.nextInt();
			switch (permission) {
			case 1:
				stat.setString(1, "Approved");
				stat.setInt(2, customerUserId);
				res = stat.executeUpdate();
				System.out.println("Account with " + customerUserId + " has been approved");
				break;
			case 2:
				stat.setString(1, "Declined");
				stat.setInt(2, customerUserId);
				res = stat.executeUpdate();
				System.out.println("Account with " + customerUserId + " has been declined");
				break;
			default:
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;

	}

	//Login of an employee
	public boolean login(int employerUserId, String Password) {
		boolean result = false;

		try {
			PreparedStatement stat = connection.prepareStatement(LOGIN_QUERY);
			stat.setInt(1, employerUserId);
			stat.setString(2, Password);
			ResultSet res = stat.executeQuery();

			if (res.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	//Employee can delete a customer
	public boolean deleteCustomer(int customerUserId) {
		boolean result = false;

		try {
			PreparedStatement stat = connection.prepareStatement(DELETE_CUSTOMER_QUERY);
			stat.setInt(1, customerUserId);

			stat.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
