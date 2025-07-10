package com.aurionpro.assignment;


public abstract class Account implements Comparable<Account>{
	
	private String accountNumber;
	private String name;
	private double balance;
	public boolean isValid;
	
	/* Constructor */
	public Account(String accountNumber, String name, double balance) {
		try {
			if(balance < 0) {
				throw new NegativeAmountException();
			}
		}
		catch(NegativeAmountException exception) {
			System.out.println(exception.getMessage());
			isValid = false;
			return;
		}
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.isValid = true;
	}
	
	/* Getters & Setters */
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	abstract void debit(double debitAmount);
	abstract void credit(double creditAmount);
	
	/* To String*/
	@Override
	public String toString() {
		return "AccountNumber: " + accountNumber + "\nName: " + name + "\nBalance: " + balance;
	}

}
