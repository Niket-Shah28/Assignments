package com.aurionpro.model;

public class OrderItem {
	private FoodItem item;
	private int quantity;
	private double unitPrice;
	private double totalPrice;
	
	public OrderItem(FoodItem item, int quantity, double unitPrice) {
		this.item = item;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = calculateTotalPrice();
	}

	
	public double calculateTotalPrice() {
		return this.quantity * this.unitPrice;
	}
	
	public FoodItem getItem() {
		return item;
	}

	public void setItemId(FoodItem item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
