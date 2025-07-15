package com.aurionpro.usersPanel;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.aurionpro.foodItem.FoodCategory;
import com.aurionpro.foodItem.FoodItem;
import com.aurionpro.foodItem.FoodItemsManager;
import com.aurionpro.orderDeliveryPartnerMapper.OrderDeliveryPartnerMapping;
import com.aurionpro.orderDeliveryPartnerMapper.OrderDeliveryPartnerMappingManager;
import com.aurionpro.payment.Payment;
import com.aurionpro.payment.PaymentManager;
import com.aurionpro.restaurants.Restaurant;
import com.aurionpro.restaurants.RestaurantManager;
import com.aurionpro.user.DeliveryPartner;
import com.aurionpro.user.DeliveryPartnerManager;

public class PanelAdmin {
	public void addRestaurant(Scanner scanner) {
		System.out.println("Enter the Restaurant Name: ");
		String restaurantName = scanner.nextLine();
		System.out.println("Enter the Address: ");
		String address = scanner.nextLine();
		RestaurantManager.addRestaurant(new Restaurant(restaurantName, address));
	}
	
	public void addDeliveryPartner(Scanner scanner) {
		System.out.println("Enter Name:");
		String name = scanner.nextLine();
		System.out.println("Enter Email: ");
		String email = scanner.nextLine();
		System.out.println("Enter Phone Number: ");
		String phone = scanner.next();
		scanner.nextLine();
		System.out.println("Enter Address: ");
		String address = scanner.nextLine();
		System.out.println("Enter the Password:");
		String password = scanner.next();
		DeliveryPartnerManager.addDeliveryPartner(name, phone, email, address, password);
	}
	
	public void addFoodItem(Scanner scanner) {
		System.out.println("Enter Name:");
		String foodItemName = scanner.nextLine();
		System.out.println("Enter Description: ");
		String description = scanner.nextLine();
		System.out.println("Enter Ingredients: ");
		String ingredients = scanner.nextLine();
		System.out.println("Enter Food Category:\n1. Indian\n2. Italian\n3. Chinese\nEnter Your Choice: ");
		int choice = scanner.nextInt();
		FoodCategory category = null;
		if(choice == 1 || choice == 2 || choice == 3) {
			if(choice == 1) {
				category = FoodCategory.INDIAN;
			}
			if(choice == 2) {
				category = FoodCategory.ITALIAN;
			}
			if(choice == 3) {
				category = FoodCategory.CHINESE;
			}
		}
		else {
			System.out.println("Invalid choice");
			return;
		}
		FoodItemsManager.addFoodItem(new FoodItem(foodItemName, description, category, ingredients));
	}
	
	public void displayAllDeliveryPartners() {
		List<DeliveryPartner> partners = DeliveryPartnerManager.getAllDeliveryPartners();
		if (partners == null || partners.isEmpty()) {
	        System.out.println("No delivery partners found.");
	        return;
	    }

	    System.out.printf("%-25s %-30s %-15s%n", "Name", "Email", "Phone Number");
	    System.out.println("-----------------------------------------------------------------------");

	    for (DeliveryPartner partner : partners) {
	        System.out.printf("%-25s %-30s %-15s%n",
	                partner.getName(),
	                partner.getEmail(),
	                partner.getPhone());
	    }
	    System.out.println();
	    System.out.println();
	}
	
	public void displayAllRestaurants() {
		List<Restaurant> restaurants = RestaurantManager.getAllRestaurants();
		
		if (restaurants == null || restaurants.isEmpty()) {
	        System.out.println("No restaurants found.");
	        return;
	    }

	    System.out.printf("%-30s %-50s%n", "Restaurant Name", "Address");
	    System.out.println("--------------------------------------------------------------------------------");

	    for (Restaurant restaurant : restaurants) {
	        System.out.printf("%-30s %-50s%n",
	                restaurant.getName(),
	                restaurant.getAddress());
	    }
	}
	
	public void displayAllFoodItems() {
		List<FoodItem> foodItems = FoodItemsManager.getAllFoodItems();
		if (foodItems == null || foodItems.isEmpty()) {
	        System.out.println("No food items found.");
	        return;
	    }

	    System.out.printf("%-25s %-35s %-20s %-40s%n", "Name", "Description", "Category", "Ingredients");
	    System.out.println("---------------------------------------------------------------------------------------------------------------");

	    for (FoodItem item : foodItems) {
	        System.out.printf("%-25s %-35s %-20s %-40s%n",
	                item.getName(),
	                item.getDescription(),
	                item.getCategory(),
	                item.getIngredients());
	    }
	}
	
	public void displayOrderDeliveryStatus() {
		Map<Integer, OrderDeliveryPartnerMapping> orderstatus = OrderDeliveryPartnerMappingManager.getAllOrderDeliveryStatus();
		if (orderstatus == null || orderstatus.isEmpty()) {
	        System.out.println("No delivery mappings found.");
	        return;
	    }

	    System.out.printf("%-10s %-25s %-30s %-15s %-25s%n", 
	        "Order ID", "Partner Name", "Partner Email", "Delivered", "Timestamp");
	    System.out.println("---------------------------------------------------------------------------------------------");

	    for (Map.Entry<Integer, OrderDeliveryPartnerMapping> entry : orderstatus.entrySet()) {
	        int orderId = entry.getKey();
	        OrderDeliveryPartnerMapping mapper = entry.getValue();
	        DeliveryPartner partner = mapper.getPartner();
	        String timestampStr = (mapper.getTimestamp() != null)
	                ? mapper.getTimestamp().toString()
	                : "N/A";

	        System.out.printf("%-10d %-25s %-30s %-15s %-25s%n",
	            orderId,
	            partner.getName(),
	            partner.getEmail(),
	            mapper.isDeliveryStatus() ? "Yes" : "No",
	            timestampStr);
	    }
	}
	
	

	public static void displayPaymentDetails() {
	    List<Payment> payments = PaymentManager.getAllPaymentDetails();
	    if (payments == null || payments.isEmpty()) {
	        System.out.println("No payments found.");
	        return;
	    }


	    System.out.printf("%-10s %-10s %-15s %-10s %-20s %-25s %-20s %-10s%n", 
	        "PaymentID", "OrderID", "Mode", "Amount", "Card Holder", "Card Number", "UPI ID", "Expiry");
	    System.out.println("--------------------------------------------------------------------------------------------------------------------");

	    for (Payment payment : payments) {
	        String cardHolder = "N/A";
	        String maskedCard = "N/A";
	        String upi = "N/A";
	        String expiry = "N/A";

	        // Card holder name
	        if (payment.getCardHolderName() != null && !payment.getCardHolderName().isEmpty()) {
	            cardHolder = payment.getCardHolderName();
	        }

	        // Masked card number
	        if (payment.getCardNumber() != null && !payment.getCardNumber().isEmpty()) {
	            String card = payment.getCardNumber();
	            if (card.length() >= 4) {
	                maskedCard = "**** **** **** " + card.substring(card.length() - 4);
	            }
	        }

	        // Masked UPI ID
	        if (payment.getUpiId() != null && !payment.getUpiId().isEmpty()) {
	            String upiId = payment.getUpiId();
	            int atIndex = upiId.indexOf("@");
	            if (atIndex > 2) {
	                upi = upiId.substring(0, 2) + "***" + upiId.substring(atIndex);
	            } else if (atIndex > -1) {
	                upi = "***" + upiId.substring(atIndex);
	            } else {
	                upi = "***";
	            }
	        }

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
	        YearMonth cardExpiry = payment.getExpiry();
	        expiry = (cardExpiry != null) ? cardExpiry.format(formatter) : "N/A";

	        System.out.printf("%-10d %-10d %-15s ₹%-9.2f %-20s %-25s %-20s %-10s%n",
	            payment.getPaymentId(),
	            payment.getOrderId(),
	            payment.getPaymentMode(),
	            payment.getAmount(),
	            cardHolder,
	            maskedCard,
	            upi,
	            expiry);
	    }
	}




	public void run(Scanner scanner) {
		System.out.println("-------------ADMIN PANEL--------------");
		outerWhile:
		while(true) {
			System.out.println();
			System.out.println();
			System.out.println("1. ADD A RESTAURANT\n2. ADD A DELIVERY PARTNER\n3. ADD A FOOD ITEM\n4. VIEW ALL DELIVERY PARTNERS"
					+ "\n5. VIEW ALL RESTAURANTS\n6. VIEW ALL FOOD ITEMS\n7. VIEW ALL DELIVERY STATUS"
					+ "\n8. VIEW PAYMENT DETAILS\n9. EXIT\nENTER YOUR CHOICE: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
				case 1:
					addRestaurant(scanner);
					break;
				case 2:
					addDeliveryPartner(scanner);
					break;
				case 3:
					addFoodItem(scanner);
					break;
				case 4:
					displayAllDeliveryPartners();
					break;
				case 5:
					displayAllRestaurants();
					break;
				case 6:
					displayAllFoodItems();
					break;
				case 7:
					displayOrderDeliveryStatus();
					break;
				case 8:
					displayPaymentDetails();
					break;
				case 9:
					break outerWhile;
				default:
					System.out.println();
					System.out.println();
					System.out.println("Invalid Choice");
			}
		}
	}
}
