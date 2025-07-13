package com.aurionpro.model;

import java.util.Scanner;

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

	public void run(Scanner scanner) {
		System.out.println("-------------ADMIN PANEL--------------");
		outerWhile:
		while(true) {
			System.out.println();
			System.out.println();
			System.out.println("1. ADD A RESTAURANT\n2. ADD A DELIVERY PARTNER\n3. ADD A FOOD ITEM\n4. EXIT\nENTER YOUR CHOICE: ");
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
					break outerWhile;
				default:
					System.out.println();
					System.out.println();
					System.out.println("Invalid Choice");
			}
		}
	}
}
