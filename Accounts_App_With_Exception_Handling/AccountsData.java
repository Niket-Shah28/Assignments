package com.aurionpro.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AccountsData {
	private Account[] accounts = new Account[10];
	private ArrayList<Account> savingsAccounts = new ArrayList<>();
	private ArrayList<Account> currentAccounts = new ArrayList<>();
	public String[] accountNumbers = new String[10];
	private int accountIndex = 0;
	public boolean isValid;
	private static int minBalance = 500;
	
	private String generateAccountNumber() {
		String accountNumber = "IDBI1000";
		Random random = new Random();
		for(int i = 0; i < 6; i++) {
			accountNumber += random.nextInt(10);
		}
		return accountNumber;
	}
	
	private boolean checkAccountNumberUniqueness(String accountNumber) {
		for(int i = 0; i < accountIndex; i++) {
			if(accountNumbers[i].equals(accountNumber)) {
				return true;
			}
		}
		return false;
	}
	
	public void createAccount(Scanner scanner) {
		System.out.println("Enter Name: ");
		String name = scanner.nextLine();
		double balance;
		while(true) {
			System.out.println("Enter Balance: ");
			balance = scanner.nextDouble();
			if(balance < minBalance) {
				System.out.println("MInimum Balance of $"+minBalance+" is required\n");
				continue;
			}
			break;
		}
		System.out.println("Account Type\n1. Savings\n2. Current\nEnter Your Choice: ");
		int choice = scanner.nextInt();
		String accountNumber = generateAccountNumber();
		boolean exists = checkAccountNumberUniqueness(accountNumber);
		while(exists) {
			accountNumber = generateAccountNumber();
			exists = checkAccountNumberUniqueness(accountNumber);
		}
		Account account;
		if(choice == 1) {
			account = new SavingsAccount(accountNumber, name, balance);
		}
		else {
			System.out.println("Enter the Overdraft Limit: ");
			double overdraftLimit = scanner.nextInt();
			account = new CurrentAccount(accountNumber, name, balance, overdraftLimit);
		}
		if(account.isValid) {
			if(choice == 1) { 
				savingsAccounts.add(account); 
			}
			else { 
				currentAccounts.add(account);
			}
			accounts[accountIndex] = account;
			accountNumbers[accountIndex] = accountNumber;
			accountIndex ++;
			isValid = true;
			System.out.println("Account Created Successfully");
		}
		else {
			isValid = false;
			System.out.println("Invalid Details");
		}
	}
	
	private Account getAccount(String accountNumber) {
		for(int i = 0; i < accountIndex; i++) {
			if(accountNumbers[i].equals(accountNumber)) {
				return accounts[i];
			}
		}
		return null;
	}
	
	public void debit(String accountNumber, double debitAmount) {
		Account account = getAccount(accountNumber);
		account.debit(debitAmount);
	}
	
	public void credit(String accountNumber, double creditAmount) {
		Account account = getAccount(accountNumber);
		account.credit(creditAmount);
	}
	
	public double getBalance(String accountNumber) {
		Account account = getAccount(accountNumber);
		return account.getBalance();
	}
	
	public void display(String accountNumber) {
		Account account = getAccount(accountNumber);
		System.out.println(account);
	}

	public ArrayList<Account> getSavingsAccounts() {
		return savingsAccounts;
	}

	public ArrayList<Account> getCurrentAccounts() {
		return currentAccounts;
	}
	
	
}
