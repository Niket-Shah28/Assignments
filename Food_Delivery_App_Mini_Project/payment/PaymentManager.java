package com.aurionpro.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	public static List<Payment> getAllPaymentDetails(){
		return new ArrayList<>(paymentManager.values());
	}
}
