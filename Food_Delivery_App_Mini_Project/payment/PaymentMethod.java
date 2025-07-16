package com.aurionpro.payment;

import java.util.Scanner;

public interface PaymentMethod {
	void pay(Scanner scanner, double amount, int orderId);
}
