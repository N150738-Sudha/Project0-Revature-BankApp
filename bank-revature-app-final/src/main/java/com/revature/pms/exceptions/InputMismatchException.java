package com.revature.pms.exceptions;

public class InputMismatchException extends RuntimeException {
	public InputMismatchException() {
		
	}
	public InputMismatchException(String msg) {
		super(msg);
	}
}
