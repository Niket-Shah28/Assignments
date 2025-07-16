package com.aurionpro.payment;

import java.time.YearMonth;

public class Payment {
	private int paymentId;
	private int orderId;
	private PaymentModes paymentMode;
	private double amount;
	private static int idCount = 1;
	private String cardNumber;
	private String upiId;
	private String cardHolderName;
	private YearMonth expiry;
	
	public Payment(int orderId, PaymentModes paymentMode, double amount) {
		this.paymentId = idCount;
		this.orderId = orderId;
		this.paymentMode = paymentMode;
		this.amount = amount;
		idCount++;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public int getOrderId() {
		return orderId;
	}

	public PaymentModes getPaymentMode() {
		return paymentMode;
	}

	public double getAmount() {
		return amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public YearMonth getExpiry() {
		return expiry;
	}

	public void setExpiry(YearMonth expiry) {
		this.expiry = expiry;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", orderId=" + orderId + ", paymentMode=" + paymentMode + ", amount="
				+ amount + ", cardNumber=" + cardNumber + ", upiId=" + upiId + ", cardHolderName=" + cardHolderName
				+ "]";
	}
}
