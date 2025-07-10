package com.aurionpro.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class AccountTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AccountsData accountsData = new AccountsData();
		
		for(int i = 0; i < 2; i++) {
			System.out.println("Enter the details for Account");
			System.out.println();
			accountsData.createAccount(scanner);
			scanner.nextLine();
		}
		System.out.println();
		System.out.println();
		
		outerWhile:
		while(true) {
			System.out.println("1. Create a new Account\n2. Choose an account\n3. Sort Savings Accounts\n4.Sort Current Accounts\n5.Exit\nEnter your choice: ");
			int choice1 = scanner.nextInt();
			switch(choice1) {
				case 1:
					scanner.nextLine();
					accountsData.createAccount(scanner);
					System.out.println();
					System.out.println();
					break;
				case 2:
		
					String account = chooseAccount(accountsData.accountNumbers, scanner);
					
					innerWhile:
					while(true) {
						System.out.println("1. View Balance\n2. Credit\n3. Debit\n4. Account Details\n5. Exit\nEnter your choice:");
						int choice = scanner.nextInt();
						switch(choice) {
							case 1:
								System.out.println("Balance: $"+accountsData.getBalance(account));
								System.out.println();
								System.out.println();
								break;
							case 2:
								System.out.println("Enter the amount to Credit:");
								double creditAmount = scanner.nextDouble();
								accountsData.credit(account, creditAmount);
								break;
							case 3:
								System.out.println("Enter the amount to Debit:");
								double debitAmount = scanner.nextDouble();
								accountsData.debit(account, debitAmount);
								break;
							case 4:
								accountsData.display(account);
								break;
							case 5: 
								break innerWhile;
							default:
								System.out.println("Invalid Choice");
								System.out.println();
								System.out.println();
						}
					}
					System.out.println();
					System.out.println();
					break;
				case 3:
					ArrayList<Account> savingsAccounts = accountsData.getSavingsAccounts();
					Collections.sort(savingsAccounts);
					System.out.println(savingsAccounts);
					break;
				case 4:
					ArrayList<Account> currentAccounts = accountsData.getCurrentAccounts();
					Collections.sort(currentAccounts);
					System.out.println(currentAccounts);
					break;
				case 5:
					break outerWhile;
				default:
					System.out.println("Invalid Choice");
					System.out.println();
					System.out.println();
			}
		}
		scanner.close();
	}
	
	public static String chooseAccount(String[] accountNumbers, Scanner scanner) {
		System.out.println("Accounts choice: ");
		for(int j = 0; j < accountNumbers.length; j++) {
			if(accountNumbers[j] != null) {
				System.out.println((j+1)+". "+accountNumbers[j]);
			}
		}
		System.out.println("Enter Your Choice: ");
		int choice = scanner.nextInt();
		System.out.println();
		System.out.println();
		return accountNumbers[choice - 1];
	}
}
