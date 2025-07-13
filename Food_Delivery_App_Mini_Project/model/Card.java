package com.aurionpro.model;

import java.util.Scanner;

public class Card implements PaymentMethod {
	
	private Payment paymentDetails;

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
        
        while (true) {
        	scanner.nextLine();
            System.out.println("Enter cardholder name: ");
            name = scanner.nextLine();

            System.out.println("Enter 16-digit card number: ");
            cardNumber = scanner.next();
            
            scanner.nextLine();

            System.out.println("Enter 3-digit CVV: ");
            cvv = scanner.next();

            if (isValidName(name) && isValidCardNumber(cardNumber) && isValidCVV(cvv)) {
                break;
            } else {
                System.out.println("Invalid input(s). Please try again.\n");
            }
        }
        paymentDetails.setCardNumber(cardNumber);
        paymentDetails.setCardHolderName(name);
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
}

