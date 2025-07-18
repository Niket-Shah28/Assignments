package com.aurionpro.orderDeliveryPartnerMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.aurionpro.user.DeliveryPartner;
import com.aurionpro.user.DeliveryPartnerManager;

public class OrderDeliveryPartnerMappingManager {
	private static Map<Integer, OrderDeliveryPartnerMapping> orderDeliveries = new HashMap<>();
	
	public static void addOrderDelivery(OrderDeliveryPartnerMapping orderDelivery) {
		orderDeliveries.put(orderDelivery.getOrderId(), orderDelivery);
		new Thread(() -> {
	        try {
	            Thread.sleep(60000); 
	            completeOrderDelivery(orderDelivery);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            System.out.println("Delivery completion thread interrupted");
	        }
	    }).start();
	}
	
	private static void completeOrderDelivery(OrderDeliveryPartnerMapping orderDelivery) {
		DeliveryPartner partner = orderDelivery.getPartner();
		orderDelivery.setDeliveryStatus(true);
		orderDelivery.setTimestamp(LocalDateTime.now());
		orderDeliveries.put(orderDelivery.getOrderId(), orderDelivery);
		DeliveryPartnerManager.releasePartner(partner);
	}
	
	public static Map<Integer, OrderDeliveryPartnerMapping> getAllOrderDeliveryStatus() {
		return orderDeliveries;
	}
}
