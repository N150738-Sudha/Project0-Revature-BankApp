package com.revature.pms.exceptions;

public class InvalidAmountException extends RuntimeException {
	public InvalidAmountException() {
		
	}
	public InvalidAmountException(String msg) {
		super(msg);
	}

}
