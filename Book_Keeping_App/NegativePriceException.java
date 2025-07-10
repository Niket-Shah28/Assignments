package com.aurionpro.assignment;

public class NegativePriceException extends RuntimeException{

	@Override
	public String getMessage() {
		return "Price cannot be negative";
	}
}
