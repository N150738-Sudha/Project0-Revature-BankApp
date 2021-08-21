package com.revature.pms.exceptions;


public class InvalidInputException extends RuntimeException {
	public InvalidInputException() {
		
	}
	public InvalidInputException(String msg) {
		super(msg);
	}
}
