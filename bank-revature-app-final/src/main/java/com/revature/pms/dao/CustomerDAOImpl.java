package com.revature.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pms.exceptions.InsufficientAmountException;
import com.revature.pms.model.Customer;
import com.revature.pms.util.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {
	//Connecting with the driver
	Connection connection = DBConnection.getDBConnection();
	
	//Writing SQL queries to execute
	private final String CREATE_ACCOUNT_QUERY = "insert into hr.customer values(?,?,?,?,?,?,?,?)";
	private final String VIEW_BALANCE_QUERY = "select customerBalance from hr.customer where customerUserId = ?";
	private final String WITHDRAW_MONEY_QUERY = "update hr.customer set customerBalance = customerBalance - ? where customerUserId = ?";
	private final String DEPOSIT_MONEY_QUERY = "update hr.customer set customerBalance = customerBalance + ? where customerUserId = ?";
	private final String CHANGE_PASSWORD_QUERY = "update hr.customer set customerPassword = ? where customerUserId = ?";
	private final String TRANSFER_TO_QUERY = "update hr.customer set customerBalance = customerBalance + ? where customerUserId = ?";
	private final String TRANSFER_FROM_QUERY = "update hr.customer set customerBalance = customerBalance - ? where customerUserId = ?";
	private final String LOGIN_QUERY = "select * from hr.customer where customerUserId = ? and customerPassword = ?";

	
	//Creating customer account
	public boolean createAccount(Customer customer) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_ACCOUNT_QUERY);
			statement.setInt(1, customer.getCustomerUserId());
			statement.setString(2, customer.getCustomerPassword());
			statement.setString(3, customer.getCustomerName());
			statement.setString(4, customer.getCustomerEmail());
			statement.setInt(5, customer.getCustomerContactNumber());
			statement.setInt(6, customer.getCustomerBalance());
			statement.setString(7, customer.getCustomerAddress());
			statement.setString(8, "In progress");
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;
	}

	//function to view balance of a customer
	public int viewBalance(int customerUserId) {
		Customer customer = new Customer();
		ResultSet res = null;
		try {
			PreparedStatement statement = connection.prepareStatement(VIEW_BALANCE_QUERY);
			statement.setInt(1, customerUserId);
			res = statement.executeQuery();
			while(res.next()) {
				customer.setCustomerBalance(res.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer.getCustomerBalance();

	}

	//function to withdrawa money
	public boolean withdrawMoney(int customerUserId, int amount) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(WITHDRAW_MONEY_QUERY);
			statement.setInt(1, amount);
			statement.setInt(2, customerUserId);
			res = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;
		
	}

	//function to deposit money
	public boolean depositMoney(int customerUserId, int amount) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(DEPOSIT_MONEY_QUERY);
			statement.setInt(1, amount);
			statement.setInt(2, customerUserId);
			res = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;
	}

	//function to change password
	public boolean changePassword(int customerUserId,String newPassword) {
		Customer customer = new Customer();
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD_QUERY);
			statement.setString(1, newPassword);
			statement.setInt(2, customerUserId);
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;
	}

	//function to transfer money
	public boolean transferMoney(int customerUserIdTransferFrom, int amount, int customerUserIdTransferTo) {
		PreparedStatement statement = null;
		int res = 0;
		try {
			statement = connection.prepareStatement(TRANSFER_TO_QUERY);
			statement.setInt(1, amount);
			statement.setInt(2, customerUserIdTransferTo);
			statement.executeUpdate();
			statement.close();

			statement = connection.prepareStatement(TRANSFER_FROM_QUERY);
			statement.setInt(1, amount);
			statement.setInt(2, customerUserIdTransferFrom);
			statement.executeUpdate();
			statement.close();
			System.out.println(res + "row(s) affected");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			return true;
	}

	//Login by the customer using id and password
	public boolean login(int customerUserId, String Password) {
		boolean result = false;

		try {
			PreparedStatement stat = connection.prepareStatement(LOGIN_QUERY);
			stat.setInt(1, customerUserId);
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

}
