package com.aurionpro.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomersManager {
	
	private static List<Customer> customers = new ArrayList<>();
	
	public static void addCustomer(Scanner scanner) {
		System.out.println("Enter Name: ");
		String name = scanner.nextLine();
		System.out.println("Enter Email ID: ");
		String email = scanner.next();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = scanner.next();
		System.out.println("Enter Password: ");
		String password = scanner.next();
		System.out.println("Enter Address: ");
		scanner.nextLine();
		String address = scanner.nextLine();
		if(ValidateUserDetails.isValid(name, phoneNumber, email, password, address)) {
			if(CustomerExists(email, phoneNumber)) {
				System.out.println("Customer already Exists !");
				return;
			}
			customers.add(new Customer(name, email, phoneNumber, address, password));
			System.out.println("Customer Added Successfully");
			return;
		}
		System.out.println("Invalid Details !");
	}
	
	// FOR STUB USE ONLY //
	public static void addCustomerDirect(String name, String phone, String email, String password, String address) {
		if(ValidateUserDetails.isValid(name, phone, email, password, address)) {
			if(CustomerExists(email, phone)) {
				System.out.println("Customer already Exists !");
				return;
			}
			customers.add(new Customer(name, email, phone, address, password));
			System.out.println("Customer Added Successfully");
			return;
		}
		System.out.println("Invalid Details !");
	}
	
	public static Map<Integer, String> getAllCustomers() {
		Map<Integer, String> currentCustomers = new LinkedHashMap<>();
		for(Customer customer:customers) {
			currentCustomers.put(customer.getId(), customer.getName());
		}
		return currentCustomers;
	}
	
	private static boolean CustomerExists(String newCustomerEmail, String newCustomerPhone){
		for(Customer existingCustomer: customers) {
			if(emailMatch(existingCustomer, newCustomerEmail) || phoneNumberMatch(existingCustomer, newCustomerPhone)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean emailMatch(Customer existingCustomer, String newCustomerEmail) {
		return existingCustomer.getEmail().equals(newCustomerEmail);
	}
	
	private static boolean phoneNumberMatch(Customer existingCustomer, String newCustomerPhone) {
		return existingCustomer.getPhone().equals(newCustomerPhone);
	}
}
