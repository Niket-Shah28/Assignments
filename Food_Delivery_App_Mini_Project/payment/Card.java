package com.aurionpro.payment;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.aurionpro.user.DeliveryPartnerManager;

public class Card implements PaymentMethod {
	
	private Payment paymentDetails;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

    @Override
    public void pay(Scanner scanner, double amount, int orderId) {
    	paymentDetails = new Payment(orderId, PaymentModes.CARD, amount);
        getDetails(scanner);
        System.out.println("₹" + amount + " debited from your account");
        PaymentManager.addPayment(paymentDetails);
        DeliveryPartnerManager.assignPartner(orderId);
    }

    public void getDetails(Scanner scanner) {
        String name;
        String cardNumber;
        String cvv;
        String expiryDate;
        YearMonth expiry;
        scanner.nextLine();
        while (true) {
        	
            System.out.println("Enter cardholder name: ");
            name = scanner.nextLine();

            System.out.println("Enter 16-digit card number: ");
            cardNumber = scanner.next();
            
            scanner.nextLine();

            System.out.println("Enter 3-digit CVV: ");
            cvv = scanner.nextLine();
            
            System.out.println("Enter the Expiry Date: (In MM/YY format)");
            expiryDate = scanner.nextLine();
            
            expiry = YearMonth.parse(expiryDate, formatter);

            if (isValidName(name) && isValidCardNumber(cardNumber) && isValidCVV(cvv) && isValidExpiry(expiry)) {
                break;
            } else {
                System.out.println("Invalid input(s). Please try again.\n");
            }
        }
        paymentDetails.setCardNumber(cardNumber);
        paymentDetails.setCardHolderName(name);
        paymentDetails.setExpiry(expiry);
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("^[A-Za-z ]+$");
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("^\\d{16}$");
    }

    private boolean isValidCVV(String cvv) {
        return cvv != null && cvv.matches("^\\d{3}$");
    }
    
    private boolean isValidExpiry(YearMonth expiry) {
    	YearMonth now = YearMonth.now();
    	return expiry.isAfter(now);
    }
}

