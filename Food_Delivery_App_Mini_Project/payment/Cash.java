package com.aurionpro.payment;

import java.util.Scanner;

import com.aurionpro.user.DeliveryPartnerManager;

public class Cash implements PaymentMethod{
	private Payment paymentDetails;
	@Override
	public void pay(Scanner scanner, double amount, int orderId) {
		System.out.println("$"+amount+" paid in Cash");
		paymentDetails = new Payment(orderId, PaymentModes.CASH, amount);
		PaymentManager.addPayment(paymentDetails);
		DeliveryPartnerManager.assignPartner(orderId);
	}
}
