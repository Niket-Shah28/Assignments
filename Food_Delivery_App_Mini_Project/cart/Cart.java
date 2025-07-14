package com.aurionpro.cart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aurionpro.foodItem.FoodItem;
import com.aurionpro.order.OrderItem;

public class Cart {
	Map<Integer, OrderItem> cart;
	
	public Cart() {
		cart = new LinkedHashMap<>();
	}
	
	public void addItem(OrderItem item) {
		int itemId = item.getItem().getId();
		if(cart.containsKey(itemId)) {
			OrderItem currItemStatus = cart.get(itemId);
			int currQuantity = currItemStatus.getQuantity();
			int newQuantity = item.getQuantity() + currQuantity;
			currItemStatus.setQuantity(newQuantity);
			double newTotalPrice = currItemStatus.calculateTotalPrice();
			currItemStatus.setTotalPrice(newTotalPrice);
			cart.put(itemId, currItemStatus);
		}
		else {
			cart.put(itemId, item);
		}
		System.out.println("Item Added Successfully !!");
	}
	
	public void removeItem(Integer itemId) {
		cart.remove(itemId);
		System.out.println("Item Removed from Cart Successfylly !!");
	}
	
	public void modifyQuantity(Integer itemId, int quantity) {
		if(quantity <= 0) {
			System.out.println("Quantity Must be greater than Zero");
			return;
		}
		OrderItem currItemStatus = cart.get(itemId);
		currItemStatus.setQuantity(quantity);
		double newTotalPrice = currItemStatus.calculateTotalPrice();
		currItemStatus.setTotalPrice(newTotalPrice);
		System.out.println("Item Modified Successfully !!");
	}
	
	public void displayCart() {
		System.out.println();
		System.out.println();
		
	    if (cart == null || cart.isEmpty()) {
	        System.out.println("Your cart is empty.");
	        return;
	    }

	    System.out.println("=========== YOUR CART ===========");
	    System.out.printf("%-4s %-20s %-10s %-12s %-12s%n", "No.", "Item Name", "Quantity", "Unit Price", "Total Price");
	    System.out.println("---------------------------------------------------------------");

	    int index = 1;
	    double cartTotal = 0.0;

	    for (OrderItem orderItem : cart.values()) {
	        FoodItem food = orderItem.getItem();
	        System.out.printf(
	            "%-4d %-20s %-10d ₹%-11.2f ₹%-11.2f%n",
	            index++,
	            food.getName(),
	            orderItem.getQuantity(),
	            orderItem.getUnitPrice(),
	            orderItem.getTotalPrice()
	        );
	        cartTotal += orderItem.getTotalPrice();
	    }

	    System.out.println("---------------------------------------------------------------");
	    System.out.printf("Total Cart Value:%39s₹%.2f%n", "", cartTotal);
	    System.out.println("==============================================");
		System.out.println();
		System.out.println();
	}
	
	
	
	public List<Integer> cartItemIdList(){
		return new ArrayList<>(cart.keySet());
	}
	
	public boolean isEmpty() {
		return cart.isEmpty();
	}
	
	public List<OrderItem> getCartItems() {
		return new ArrayList<>(cart.values());
	}

}
