package com.revature.pms.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.pms.model.Customer;
import com.revature.pms.dao.CustomerDAOImpl;

public class EmployeeDAOImplTest extends CustomerDAOImpl{
	private	EmployeeDAO employeeDAO ;
	private	CustomerDAO customerDAO = new CustomerDAOImpl();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		employeeDAO = new EmployeeDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		employeeDAO = null;
	}

	@Test
	public void testCreatAccount() {

	}

	@Test
	public void testGetAllCustomerDetails() {
		int customerIdToTest = 99;
		List<Customer> originalCustomerCount1 = employeeDAO.getAllCustomerDetails(3);
		customerDAO.createAccount(new Customer(customerIdToTest,"123","name","email",123,1000,"address","In"));
		List<Customer> originalCustomerCount2 = employeeDAO.getAllCustomerDetails(3);

		
		assertEquals(originalCustomerCount2.size(),originalCustomerCount1.size()+1);
		employeeDAO.deleteCustomer(customerIdToTest);
		}

	

	@Test
	public void testDeleteCustomer() {
		List<Customer> originalCustomerCount1 = employeeDAO.getAllCustomerDetails(3);
		customerDAO.createAccount(new Customer(90,"123","name","email",123,1000,"address","In"));
		employeeDAO.deleteCustomer(90);
		List<Customer> originalCustomerCount2 = employeeDAO.getAllCustomerDetails(3);

		
		assertEquals(originalCustomerCount2.size(),originalCustomerCount1.size());
		
	}

}
