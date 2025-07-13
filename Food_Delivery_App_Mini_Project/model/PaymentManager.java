package com.aurionpro.model;

import java.util.HashMap;
import java.util.Map;

public class PaymentManager {
	private static Map<Integer, Payment> paymentManager = new HashMap<>();
	
	public static void addPayment(Payment payment) {
		paymentManager.put(payment.getOrderId(), payment);
		System.out.println();
		System.out.println();
		System.out.println("Payment Added Successfully");
		System.out.println();
		System.out.println();
	}
}
