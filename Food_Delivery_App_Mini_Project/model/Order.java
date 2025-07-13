package com.aurionpro.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	
	private static final double discountPercentage = 10;
	private final int id;
	private final List<OrderItem> items;
	private final int restaurantId;
	private final int customerId;
	private final double totalPrice;
	private final double finalPrice;
	private final LocalDateTime timestamp;
	private final String restaurantName;
	private final String instruction;
	private static int idCount = 1;
	
	public Order(List<OrderItem> items, int restaurantId, int customerId, String restaurantName, String instruction) {
		this.items = items;
		this.totalPrice = calculateTotalPrice(items);
		this.finalPrice = calculateFinalPrice();
		this.timestamp = LocalDateTime.now();
		this.restaurantId = restaurantId;
		this.customerId = customerId;
		this.restaurantName = restaurantName;
		this.instruction = instruction;
		this.id = idCount;
		idCount++;
	}
	
	private double calculateTotalPrice(List<OrderItem> orderItems) {
		double totalPrice = 0;
		for(OrderItem item:orderItems) {
			totalPrice += item.getTotalPrice();
		}
		return totalPrice;
	}
	
	private double calculateFinalPrice() {
		if(totalPrice > 500) {
			return totalPrice - (0.01*discountPercentage*totalPrice);
		}
		return totalPrice;
	}

	public int getId() {
		return id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String getInstruction() {
		return instruction;
	}
	
	public double getFinalPrice() {
		return finalPrice;
	}
}
