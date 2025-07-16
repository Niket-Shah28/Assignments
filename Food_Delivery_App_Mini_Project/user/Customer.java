package com.aurionpro.user;

public class Customer extends User{
	
	private int loyaltyPoints;

	public Customer(String name, String email, String phone, String address, String password) {
		super(name, email, phone, address, password);
		loyaltyPoints = 0;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
}
