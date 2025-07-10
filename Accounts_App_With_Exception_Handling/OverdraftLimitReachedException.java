package com.aurionpro.assignment;

public class OverdraftLimitReachedException extends RuntimeException{
	private double overdrafRemaining;
	
	
	
	public OverdraftLimitReachedException(double overdrafRemaining) {
		super();
		this.overdrafRemaining = overdrafRemaining;
	}

	public String getMessage() {
		return "Overdraft Limit Recahed. Overdraft Limit remaining is: $"+overdrafRemaining;
	}
}
