package com.aurionpro.assignment;

public class NegativeAmountException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "Amount to be deposited / credited must be greather than Zero";
	}
}
