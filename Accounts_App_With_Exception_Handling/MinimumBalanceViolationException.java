package com.aurionpro.assignment;

public class MinimumBalanceViolationException extends RuntimeException{
	private double minimumBalance;
	
	public MinimumBalanceViolationException(double minimumBalance) {
		super();
		this.minimumBalance = minimumBalance;
	}

	@Override
	public String getMessage() {
		return "Minimum balanec of $"+minimumBalance+" is required.";
	}
}
