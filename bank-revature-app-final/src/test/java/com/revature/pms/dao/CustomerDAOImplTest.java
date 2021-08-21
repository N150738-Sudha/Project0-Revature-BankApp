package com.revature.pms.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.revature.pms.dao.EmployeeDAOImpl;
import com.revature.pms.model.Customer;

public class CustomerDAOImplTest extends EmployeeDAOImpl{
	private CustomerDAO customerDAO;
	private	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		customerDAO = new CustomerDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		customerDAO = null;
	}

	@Test
	public void testCreatAccount() {
		List<Customer> originalCustomerCount1 = employeeDAO.getAllCustomerDetails(3);
		customerDAO.createAccount(new Customer(90,"123","name","email",123,1000,"address","In"));
		List<Customer> originalCustomerCount2 = employeeDAO.getAllCustomerDetails(3);

		
		assertEquals(originalCustomerCount2.size(),originalCustomerCount1.size()+1);
		employeeDAO.deleteCustomer(90);
	}

	@Test
	public void testViewBalance() {
		customerDAO.createAccount(new Customer(90,"123","name","email",123,1000,"address","In"));
		int balance = customerDAO.viewBalance(90);
		assertEquals(1000,balance);
		employeeDAO.deleteCustomer(90);
	}

	@Test
	public void testWithdrawMoney() {
		customerDAO.createAccount(new Customer(90,"123","name","email",123,1000,"address","In"));
		customerDAO.withdrawMoney(90, 500);
		int balance = customerDAO.viewBalance(90);
		assertEquals(500,balance);
		employeeDAO.deleteCustomer(90);
	}

	@Test
	public void testDepositMoney() {
		customerDAO.createAccount(new Customer(90,"123","name","email",123,1000,"address","In"));
		customerDAO.depositMoney(90, 500);
		int balance = customerDAO.viewBalance(90);
		assertEquals(1500,balance);
		employeeDAO.deleteCustomer(90);
	}


	@Test
	public void testTransferMoney() {
		customerDAO.createAccount(new Customer(90,"123","name","email",123,1000,"address","In"));
		customerDAO.createAccount(new Customer(91,"123","name","email",123,1000,"address","In"));
		customerDAO.transferMoney(90,500,91);
		int balance91 = customerDAO.viewBalance(91);
		assertEquals(1500,balance91);
		employeeDAO.deleteCustomer(90);
		employeeDAO.deleteCustomer(91);
	}


}
