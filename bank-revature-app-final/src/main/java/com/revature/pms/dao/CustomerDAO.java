package com.revature.pms.dao;

import com.revature.pms.model.Customer;

public interface CustomerDAO {
	public boolean createAccount(Customer customer);
	public int viewBalance(int customerUserId);
	public boolean withdrawMoney(int customerUserId,int amount);
	public boolean depositMoney(int customerUserId,int amount);
	public boolean changePassword(int customerUserId,String newPassword);
	public boolean transferMoney(int customerUserIdTransferFrom,int amount,int customerUserIdTransferTo);
	public boolean login(int customerUserId,String Password);
}
