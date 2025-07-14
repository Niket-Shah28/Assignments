package com.aurionpro.payment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.aurionpro.user.DeliveryPartnerManager;

public class UPI implements PaymentMethod {

    private static final Set<String> VALID_UPI_DOMAINS = new HashSet<>(Arrays.asList(
        "ybl", "upi", "oksbi", "okhdfcbank", "okaxis", "okicici", "paytm", "airtel", "jio"
    ));
    private Payment paymentDetails;

    @Override
    public void pay(Scanner scanner, double amount, int orderId) {
    	paymentDetails = new Payment(orderId, PaymentModes.UPI, amount);
        getDetails(scanner);
        System.out.println("₹" + amount + " debited from your UPI account.");
        PaymentManager.addPayment(paymentDetails);
        DeliveryPartnerManager.assignPartner(orderId);
    }

    public void getDetails(Scanner scanner) {
        String upiId;
        String password;
        System.out.println();
		System.out.println();

        while (true) {
            System.out.println("Enter UPI ID (e.g., user@upi): ");
            upiId = scanner.next();
            System.out.println("Enter 4 or 6 digit numeric password: ");
            password = scanner.next();

            if (isValidUpiId(upiId) && isValidPassword(password)) {
                break;
            } else {
                System.out.println("Invalid UPI ID or password. Please try again.\n");
            }
        }
        paymentDetails.setUpiId(upiId);
        System.out.println();
		System.out.println();
    }

    private boolean isValidUpiId(String upiId) {
        if (upiId == null || !upiId.contains("@")) return false;
        String[] parts = upiId.split("@");
        if (parts.length != 2) return false;

        String domain = parts[1].toLowerCase();
        return isNotBlank(parts[0]) && VALID_UPI_DOMAINS.contains(domain);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches("^\\d{4}$|^\\d{6}$");
    }

    private boolean isNotBlank(String input) {
        return input != null && !input.trim().isEmpty();
    }
}

