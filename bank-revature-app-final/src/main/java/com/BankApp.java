package com;

import java.util.Date;
import java.util.InputMismatchException;
import org.apache.log4j.*;
import java.util.List;
import java.util.Scanner;

import com.revature.pms.dao.CustomerDAO;
import com.revature.pms.dao.CustomerDAOImpl;
import com.revature.pms.dao.EmployeeDAO;
import com.revature.pms.dao.EmployeeDAOImpl;
import com.revature.pms.exceptions.InsufficientAmountException;
import com.revature.pms.exceptions.InvalidAmountException;
import com.revature.pms.exceptions.InvalidInputException;
import com.revature.pms.model.Customer;
import com.revature.pms.model.Employee;


public class BankApp {
	
	int option = 0;
	Customer customer = new Customer();
	CustomerDAO customerDAO = new CustomerDAOImpl();
	Employee employer = new Employee();
	EmployeeDAO employerDAO = new EmployeeDAOImpl();
	boolean result;
	private static Logger logger = Logger.getLogger("BankApp");
	
	//Method to call all user functions
	public void startBankApp() throws InvalidInputException, InsufficientAmountException, InvalidAmountException {
		System.out.println("Please enter your name :");
		Scanner sname = new Scanner(System.in);
		String username = sname.next();
		
		logger.info("Welcome, "+username+" to Bank App...you logged in at:"+new Date());
		while (true) {
			System.out.println("******************** MENU **********************");
			System.out.println("		1. Login");
			System.out.println("		2. create a new account");
			System.out.println("		3. Know more about Banking app");
			System.out.println("		4. Know about developers");
			System.out.println("		5. Help");
			System.out.println("		6. FAQ(Frequently Asked Question)");
			System.out.println("		7. Exit.......");
			System.out.println("Enter your option :");
			Scanner o = new Scanner(System.in);
			try {
				option = o.nextInt();

				switch (option) {
				case 1:
					System.out.println("Enter your account type \n 'E' for Employee \n 'C' for Customer");
					Scanner sc = new Scanner(System.in);
					char accountType = sc.next().charAt(0);
					switch (accountType) {
					
					//Executing customer user functions
					case 'C' | 'c':
						Customer customerDetails = new Customer();
						int choice = 0;
						customer = acceptCustomerLoginDetails();
						customerDetails = employerDAO.getCustomerById(customer.getCustomerUserId());
						if (customerDAO.login(customer.getCustomerUserId(), customer.getCustomerPassword())) {
							if ((customerDetails.getCustomerStatus()).equals("In progress")) {
								logger.error("Customer account with customerId "+customer.getCustomerUserId()+" in progress, not yet approved.......");
							} else if ((customerDetails.getCustomerStatus()).equals("Declined")) {
								logger.error("Customer account with customerId "+customer.getCustomerUserId()+" been declined.......");
							} else {
								boolean exit = true;
								while (exit) {
									System.out.println("******* What do you want? ********");
									System.out.println("		1. View Balance");
									System.out.println("		2. Withdraw money");
									System.out.println("		3. Deposit money");
									System.out.println("		4. Change password");
									System.out.println("		5. Transfer money");
									System.out.println("		6. Exit()");
									System.out.println("enter your choice :");
									Scanner in = new Scanner(System.in);
									choice = in.nextInt();
									switch (choice) {
									
									//case to view balance of a customer
									case 1:
										int balance = customerDAO.viewBalance(customer.getCustomerUserId());
										logger.info("Current balance of customer with customerId "+customer.getCustomerUserId()+" INR " + balance);
										break;
										
									//Withdrawing money from account
									case 2:
										System.out.println("enter the amount to be withdrawn :");
										Scanner sw = new Scanner(System.in);
										int amountToWithdraw = sw.nextInt();
										
										//verifying whether sufficient balance is there in the account or not
										if (amountToWithdraw > customerDAO.viewBalance(customer.getCustomerUserId())) {
											throw new InsufficientAmountException(
													"Insufficient balance in your account");
											
										} 
										
										//verifying whether the entered money value is positive or not
										else if (amountToWithdraw < 0) {
											throw new InvalidAmountException("You have entered negative amount");
										} 
										else {
											if (customerDAO.withdrawMoney(customer.getCustomerUserId(),
													amountToWithdraw))
												logger.info("Rs " + amountToWithdraw+ " is withdrawn from the account with Id "+customer.getCustomerUserId()+" and the remaining balance is INR "
													+ customerDAO.viewBalance(customer.getCustomerUserId()));
											else
												logger.error("Amount is not withdrawan");
										}
										break;
									
									//Depositing money into the account by the customer
									case 3:
										System.out.println("enter the amount to deposit :");
										Scanner sd = new Scanner(System.in);
										int amountToDeposit = sd.nextInt();
										
										//verifying whether the depositing money value is positive or not
										if (amountToDeposit < 0) {
											throw new InvalidAmountException("You have entered negative amount");
										} else {
											if (customerDAO.depositMoney(customer.getCustomerUserId(), amountToDeposit))
												logger.info("Rs " + amountToDeposit
														+ " is deposited in the account with customerId "+customer.getCustomerUserId()+" and the new balance is INR "
														+ customerDAO.viewBalance(customer.getCustomerUserId()));
											else
												logger.error("Amount is not deposited");
										}
										break;
										
									//Changing password by the customer
									case 4:
										System.out.println("Enter your new password :");
										Scanner sp = new Scanner(System.in);
										String newPassword = sp.nextLine();
										if (customerDAO.changePassword(customer.getCustomerUserId(), newPassword))
											logger.info("customer with Id "+customer.getCustomerUserId()+" has been updated");
										else
											logger.error("Password is not updated");
										break;
										
									//Transferring money to other customer account
									case 5:
										Scanner st = new Scanner(System.in);
										System.out.println("enter the customerUserId to transfer the money");
										int creditId = st.nextInt();
										System.out.println("Enter the amount to transfer :");
										int transferAmount = st.nextInt();
										
										//verifying whether the account is own or other
										if (customer.getCustomerUserId() == creditId) {
											logger.error("Money cannot be transferred to same account");
										}
										
										else if (customerDAO.transferMoney(customer.getCustomerUserId(), transferAmount,
												creditId)) {
											logger.info("Transferred INR " + transferAmount + " from account :"
													+ customer.getCustomerUserId() + " to " + creditId
													+ " successfully");

										System.out.println();
										}
										else
											logger.error("Money is not transferred");
										break;
									case 6:
										exit = false;
										break;
									}
								}
							}
						} else
							System.out.println("Account doesn't exist or password is incorrect");
						break;
						
					//executing employee user stories
					case 'E' | 'e':
						boolean select = true;
						int selection = 0;
						employer = acceptEmployeeLoginDetails();
						if (employerDAO.login(employer.getEmployerUserId(), employer.getEmployerPassword())) {
							while (select) {
								System.out.println("****** What do you want? ******");
								System.out.println("		1. Get all customer details");
								System.out.println("		2. Get customer details by customerUserId");
								System.out.println("		3. Get customer details by customerName");
								System.out.println("		4. Giving permissions to customers");
								System.out.println("		5. Delete a customer using customer id");
								System.out.println("		6.Exit()");
								System.out.println("enter your choice :");
								Scanner in = new Scanner(System.in);
								selection = in.nextInt();
								switch (selection) {
								
								//Getting all customer details
								case 1:
									int detailsType = 0;
									System.out.println("Select one option from below option");
									System.out.println("1.Get customer details with account status as progress");
									System.out.println("2.Account approved customer details");
									System.out.println("3.Get all customers details");
									Scanner ss = new Scanner(System.in);
									detailsType = ss.nextInt();
									List<Customer> customers = employerDAO.getAllCustomerDetails(detailsType);
									System.out.println("###Printing all customer details");
									System.out.println(customers);
									break;
									
								//To view details of customer using customerId
								case 2:
									Scanner si = new Scanner(System.in);
									System.out.println("Enter customerUserId to view details");
									int customerUserId = si.nextInt();
									customer = employerDAO.getCustomerById(customerUserId);
									System.out.println(customer);
									break;
									
								//To view details of customer using customerName
								case 3:
									Scanner sn = new Scanner(System.in);
									System.out.println("Enter customerName to view details");
									String customerName = sn.nextLine();
									List<Customer> customersWithSameName = employerDAO.getCustomerByName(customerName);
									System.out.println(customersWithSameName);
									break;
								
								//Verifying the customer account by employee 
								case 4:
									System.out.println("Enter customerUserId");
									Scanner sa = new Scanner(System.in);
									int customerUserIdRequest = sa.nextInt();
									result = employerDAO.acceptOrDecline(customerUserIdRequest);
									if (result)
										logger.info("Customer status with Id "+customer.getCustomerUserId()+" has been progressed");
									else
										logger.info("Customer status with Id "+customer.getCustomerUserId()+" is still in progres.....");
									break;
								
								
								//Deleting a customer account
								case 5 :
									System.out.println("Enter customerId to delete:");
									Scanner sn1 = new Scanner(System.in);
									int customerUserId1 = sn1.nextInt();
									result = employerDAO.deleteCustomer(customerUserId1);
									if (result)
										logger.info("Customer status with Id "+customerUserId1+" has been deleted");
									else
										logger.info("Customer status with Id "+customerUserId1+" is not yet deleted");
									break;
								case 6:
									select = false;
									break;
											
								default:
									logger.error("Please enter valid input only.......");
									break;
								}
							}
						}
					default:
						throw new InvalidInputException("Choose either E or C only");
					}
					break;
				
				
				//Creating a new account by a customer and an employee
				case 2:
					logger.info("*******Create a new account**********");
					System.out.println("Enter your account type \n 'E' for Employee \n 'C' for Customer");
					Scanner sa = new Scanner(System.in);
					char accountTypeToCreate = sa.next().charAt(0);
					switch (accountTypeToCreate) {
					
					//Creating an account by employee
					case 'E':
						employer = acceptEmployerDetails();
						employerDAO.creatAccount(employer);
						logger.info("Account with name "+employer.getEmployerName()+" is created successfully and the account status is in progress.....");
						break;
						
					//creating an account by customer
					case 'C':
						customer = acceptCustomerDetails();
						customerDAO.createAccount(customer);
						System.out.println("Account with name "+customer.getCustomerName()+" is created successfully");
						break;
					default:
						throw new InputMismatchException("Please enter valid input only.......");
					}
					break;
					
				//About banking app
				case 3:
					logger.info("*********About Banking App**********");
					System.out.println(
							"# You can access the details of your bank account \n # You can complete your transactions directly from phone,tablet or mobile device \n "
									+ "# You can withdraw or deposit money \n");
					break;
					
				//About developer
				case 4:
					logger.info("*********Developer details**********");
					logger.info("Developed by Konari Sudharani");
					break;
					
				//App help
				case 5:
					logger.info("Help Line Number : 00000000");
					break;
					
				//frequently asked questions
				case 6:
					logger.info("Frequently used questions");
					System.out.println("1. What is BankApp Mobile Banking? \n\n BankApp Mobile Banking is a mobile application that allows you to access your bank account(s) using a mobile phone. You can view account related information, transfer funds, pay bills and recharge your mobile and a lot more using this application.Download the application now to make banking a lot more simpler!");
					System.out.println("\n\n2. Who can use BankApp?\n\n An existing BankApp customer with a savings/current account registered with either Internet banking or having an ATM cum Debit Card along with mobile number registered for SMS Banking can avail the mobile banking facility.");
					System.out.println("\n\n3. How do I download BankApp?\n\n Axis Mobile can be downloaded from - Google Play Store for Android devices, App store for Apple devices, Nokia store for Windows devices.");
					break;
					
				case 7:
					logger.info("##Thanks for using my bank app");
					System.exit(0);
					break;
				default:
					throw new InvalidInputException("Please choose given options only");
				}
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			 }catch(InsufficientAmountException e) {
				 System.out.println(e.getMessage());
			 }catch (InvalidAmountException e) {
				 System.out.println(e.getMessage());
			 }catch (InvalidInputException e) {
				 System.out.println(e.getMessage());
			 }
		}
	}

	//Method to accept customer login details to verify login
	public Customer acceptCustomerLoginDetails() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter customerUserId :");
		int customerUserId = input.nextInt();
		System.out.println("Enter your password :");
		String customerPassword = input.next();
		Customer customer = new Customer(customerUserId, customerPassword);
		return customer;
	}

	//Method to accept employee login details to verify login
	public Employee acceptEmployeeLoginDetails() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter employerUserId :");
		int employerUserId = input.nextInt();
		System.out.println("Enter your password :");
		String employerPassword = input.next();
		Employee employer = new Employee(employerUserId, employerPassword);
		return employer;
	}

	//getting customer details to create account
	public Customer acceptCustomerDetails() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter customerUserId :");
		int customerUserId = input.nextInt();
		System.out.println("Enter your password :");
		String customerPassword = input.next();
		System.out.println("Enter your name :");
		String customerName = input.next();
		System.out.println("Enter your email :");
		String customerEmail = input.next();
		System.out.println("Enter your mobile number :");
		int customerContactNumber = input.nextInt();
		System.out.println("Enter your initial balance :");
		int customerInitialBalance = input.nextInt();
		System.out.println("Enter your address :");
		String customerAddress = input.next();

		Customer customer = new Customer(customerUserId, customerPassword, customerName, customerEmail,
				customerContactNumber, customerInitialBalance, customerAddress, "In progress");
		return customer;
	}

	//getting employee details to create account
	public Employee acceptEmployerDetails() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter employerUserId :");
		int employerUserId = input.nextInt();
		System.out.println("Enter your password :");
		String employerPassword = input.next();
		System.out.println("Enter your name :");
		String employerName = input.next();
		System.out.println("Enter your email :");
		String employerEmail = input.next();
		System.out.println("Enter your mobile number :");
		int employerContactNumber = input.nextInt();
		System.out.println("Enter your initial balance :");
		int employerInitialBalance = input.nextInt();
		System.out.println("Enter your address :");
		String employerAddress = input.next();

		Employee employer = new Employee(employerUserId, employerPassword, employerName, employerEmail,
				employerContactNumber, employerInitialBalance, employerAddress);
		return employer;
	}

}
