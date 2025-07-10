package com.aurionpro.assignment;

public class InvalidYearException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "Invalid Year";
	}
}
