package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

public class DeliveryPartnerManager {
	private static List<DeliveryPartner> availableDeliveryPartners = new ArrayList<>();
	private static List<DeliveryPartner> busyDeliveryPartners = new ArrayList<>();
	
	public static void addDeliveryPartner(String name, String phone, String email, String password, String address) {
		
		if(ValidateUserDetails.isValid(name, phone, email, password, address)) {
			if(deliveryPartnerExists(email, phone)) {
				System.out.println("Delivery Partner already Exists !");
				return;
			}
			availableDeliveryPartners.add(new DeliveryPartner(name, email, phone, address, password));
			System.out.println("Delivery Partner Added Successfully");
			return;
		}
		System.out.println("Invalid Details ! ");
	}
	
	public static void assignPartner(Integer orderId) {
		if(availableDeliveryPartners.size() == 0) {
			System.out.println("No Delivery Partners Available");
			return;
		}
		DeliveryPartner partner = availableDeliveryPartners.remove(0);
		busyDeliveryPartners.add(partner);
		System.out.println("Partner Assigned: "+partner.getName());
		OrderDeliveryPartnerMappingManager.addOrderDelivery(new OrderDeliveryPartnerMapping(orderId, partner));
	}
	
	public static void releasePartner(DeliveryPartner partner) {
		busyDeliveryPartners.remove(partner);
		availableDeliveryPartners.add(partner);
		System.out.println("Partner released: "+partner.getName());
	}
	
	private static boolean deliveryPartnerExists(String newPartnerEmail, String newPartnerPhoneNumber){
		List<DeliveryPartner> partners = new ArrayList<>();
		partners.addAll(availableDeliveryPartners);
		partners.addAll(busyDeliveryPartners);
		for(DeliveryPartner existingPartner: partners) {
			if(emailMatch(existingPartner, newPartnerEmail) || phoneNumberMatch(existingPartner, newPartnerPhoneNumber)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean emailMatch(DeliveryPartner existingPartner, String newPartnerEmail) {
		return existingPartner.getEmail().equals(newPartnerEmail);
	}
	
	private static boolean phoneNumberMatch(DeliveryPartner existingPartner, String newPartnerPhoneNumber) {
		return existingPartner.getPhone().equals(newPartnerPhoneNumber);
	}
}
