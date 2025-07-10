package com.aurionpro.assignment;

public class SavingsAccount extends Account implements Comparable<Account>{

	private static int minBalance = 500;
	/* Constructor */
	public SavingsAccount(String accountNumber, String name, double balance) {
		super(accountNumber, name, balance);
		try {
			if(balance < minBalance) {
				throw new MinimumBalanceViolationException(minBalance);
			}
		}
		catch(MinimumBalanceViolationException exception){
			isValid = false;
			System.out.println(exception.getMessage());
		}
	}
	
	/* Methods */
	@Override
	public void credit(double creditAmount) {
		try {
			if(creditAmount < 0) {
				throw new NegativeAmountException();
			}
			setBalance(getBalance() + creditAmount);
			System.out.println("$"+creditAmount+" Credited Successfully");
			return;
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
			if(getBalance() - debitAmount < minBalance) {
				throw new MinimumBalanceViolationException(minBalance);
			}
			setBalance(getBalance() - debitAmount);
			System.out.println("$"+debitAmount+" Debitted Successfully");
		}
		catch(NegativeAmountException exception) {
			System.out.println(exception.getMessage());
		}
		catch(MinimumBalanceViolationException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Override
	public String toString() {
		System.out.println();
		System.out.println();
		return super.toString()+"\nAccount Type: SAVINGS\n\n";
	}
	

	@Override
	public int compareTo(Account o) {
		// TODO Auto-generated method stub
		return Double.compare(this.getBalance(), o.getBalance());
	}
	
	
}
