package com.aurionpro.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManager {
	private static Map<Integer, List<Order>> orders = new HashMap<>();
	
	public static void addOrder(Order order) {
		int customerId = order.getCustomerId();
		if(!orders.containsKey(customerId)) {
			List<Order> orderList = new ArrayList<>();
			orderList.add(order);
			orders.put(customerId, orderList);
		}
		else {
			List<Order>currentOrders = orders.get(customerId);
			currentOrders.add(order);
			orders.put(customerId, currentOrders);
		}
		System.out.println("Order added Successfully");
	}
	
	public static List<Order> getAllOrdersOfCustomer(int customerId) {
		return orders.get(customerId);
	}
}
