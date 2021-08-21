package com.revature.pms.exceptions;

public class InsufficientAmountException extends RuntimeException{
	public InsufficientAmountException(String msg) {
		super(msg);
	}

}
