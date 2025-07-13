package com.aurionpro.model;

import java.util.Scanner;

public interface PaymentMethod {
	void pay(Scanner scanner, double amount, int orderId);
}
