package com.aurionpro.model;

public class DeliveryPartner extends User{
	
	private double rating;
	private int deliveryCount;

	public DeliveryPartner(String name, String email, String phone, String address, String password) {
		super(name, email, phone, address, password);
		deliveryCount = 0;
		rating = 0;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getDeliveryCount() {
		return deliveryCount;
	}

	public void setDeliveryCount(int deliveryCount) {
		this.deliveryCount = deliveryCount;
	}
}
