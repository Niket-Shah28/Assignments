package com.aurionpro.orderDeliveryPartnerMapper;

import java.time.LocalDateTime;

import com.aurionpro.user.DeliveryPartner;

public class OrderDeliveryPartnerMapping {
	private final int orderId;
	private final DeliveryPartner partner;
	private boolean deliveryStatus = false;
	private LocalDateTime timestamp;
	
	public OrderDeliveryPartnerMapping(int orderId, DeliveryPartner partner) {
		super();
		this.orderId = orderId;
		this.partner = partner;
	}

	public int getOrderId() {
		return orderId;
	}

	public DeliveryPartner getPartner() {
		return partner;
	}

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
