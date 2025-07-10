package com.aurionpro.assignment;

public class CurrentAccount extends Account{
	private double overdraftLimit;
	private double overdraftConsumed = 0;
	private double overdraftRemaining;
	
	/* Constructor */
	public CurrentAccount(String accountNumber, String name, double balance, double overdraftLimit) {
		super(accountNumber, name, balance);
		try {
			this.overdraftLimit = overdraftLimit;
			overdraftRemaining = overdraftLimit;
		}
		catch(NegativeAmountException exception) {
			System.out.println(exception.getMessage());
			isValid = false;
		}
	}
	
	/* Getters & Setters */
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	public double getOverdraftConsumed() {
		return overdraftConsumed;
	}
	public double getOverdraftRemaining() {
		return overdraftRemaining;
	}
	
	/* Methods */
	@Override
	public void credit(double creditAmount) {
		try {
			if(creditAmount < 0) {
				throw new NegativeAmountException();
			}
			overdraftConsumed -= creditAmount;
			if(overdraftConsumed > 0) {
				overdraftRemaining = overdraftLimit - overdraftConsumed;
				System.out.println("$"+creditAmount+" credited Successfully");
				return;
			}
			setBalance(getBalance() + Math.abs(overdraftConsumed));
			overdraftConsumed = 0;
			overdraftRemaining = overdraftLimit;
			System.out.println("$"+creditAmount+" credited Successfully");
		}
		catch(NegativeAmountException exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	@Override
	public void debit(double debitAmount) {
		try {
			if(debitAmount < 0) {
				throw new NegativeAmountException();
			}
			if(getBalance() - debitAmount > 0) {
				setBalance(getBalance() - debitAmount);
				System.out.println("$"+debitAmount+" Debitted Successfully");
				return;
			}
			if(overdraftRemaining + getBalance() < debitAmount) {
				throw new OverdraftLimitReachedException(overdraftRemaining);
			}
			this.overdraftConsumed = this.overdraftConsumed + Math.abs(getBalance() - debitAmount);
			overdraftRemaining = overdraftLimit - overdraftConsumed;
			setBalance(0);
			return;
		}
		catch(NegativeAmountException exception) {
			System.out.println(exception.getMessage());
		}
		catch(OverdraftLimitReachedException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Override
	public String toString() {
		System.out.println();
		System.out.println();
		return super.toString() + "\nAccount Type: CURRENT\nOverdraft Limit: " + this.overdraftLimit + "\nOverdraft Consumed: " + this.overdraftConsumed
				+ "\nOverdraft Remaining: " + this.overdraftRemaining+"\n\n";
	}

	@Override
	public int compareTo(Account o) {
		return Double.compare(this.getBalance(), o.getBalance());
	}
	
	
}
