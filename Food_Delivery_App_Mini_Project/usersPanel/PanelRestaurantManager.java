package com.aurionpro.usersPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.aurionpro.foodItem.FoodCategory;
import com.aurionpro.foodItem.FoodItem;
import com.aurionpro.foodItem.FoodItemsManager;
import com.aurionpro.restaurantFoodItemMapper.RestaurantFoodItem;
import com.aurionpro.restaurantFoodItemMapper.RestaurantFoodItemManager;
import com.aurionpro.restaurants.Restaurant;
import com.aurionpro.restaurants.RestaurantManager;
import com.aurionpro.restaurants.RestaurantMenu;

public class PanelRestaurantManager {
	
	public void displayRestaurantMenu(int restaurantId) {
	    System.out.println();
	    System.out.println();

	    List<RestaurantFoodItem> menu = RestaurantMenu.getRestaurantMenu(restaurantId);
	    System.out.println();

	    if (menu != null && !menu.isEmpty()) {
	        System.out.println("MENU");
	        System.out.println("---------");

	        Map<FoodCategory, List<RestaurantFoodItem>> categorizedMenu = new HashMap<>();

	        for (RestaurantFoodItem item : menu) {
	            FoodCategory category = item.getItem().getCategory();
	            categorizedMenu
	                .computeIfAbsent(category, k -> new ArrayList<>())
	                .add(item);
	        }
	        int idCount = 1;
	        for (Map.Entry<FoodCategory, List<RestaurantFoodItem>> entry : categorizedMenu.entrySet()) {
	            FoodCategory category = entry.getKey();
	            List<RestaurantFoodItem> items = entry.getValue();

	            System.out.println();
	            System.out.println(category.name() + " ITEMS");
	            System.out.println("------------------------------");
	            System.out.printf("%-4s %-25s %-10s%n", "No.", "Item Name", "Price");

	            for (RestaurantFoodItem foodItem:items) {
	                System.out.printf(
	                    "%-4d %-25s ₹%-9.2f%n",
	                    (idCount++),
	                    foodItem.getItem().getName(),
	                    foodItem.getPrice()
	                );
	            }
	        }

	        return;
	    }

	    System.out.println("No Items in menu");
	    System.out.println();
	    System.out.println();
	}
	
	public void displayRestaurants() {
		System.out.println();
		System.out.println();
		System.out.println("RESTAURANTS");
		System.out.println("---------------------------");
		int index = 1;
		for(Entry<Integer,String> entry:RestaurantManager.getRestaurants().entrySet()) {
			System.out.println(index+". "+entry.getValue());
			index ++;
		}
		System.out.println();
		System.out.println();
	}
	
	public int getRestaurantId(int choice) {
		int index = 1;
		for(Entry<Integer,String> entry:RestaurantManager.getRestaurants().entrySet()) {
			if(index == choice) {
				return entry.getKey();
			}
			index ++;
		}
		return -1;
	}
	private void displayItems(Set<FoodItem> items) {
		int index = 1;
		for(FoodItem item: items) {
			System.out.println(index+". "+item.getName());
			index ++;
		}
		System.out.println();
		System.out.println();
	}
	
	private FoodItem getChosenItem(Set<FoodItem>remainingItems, int choice) {
		int index = 1;
		for(FoodItem item: remainingItems) {
			if(index == choice) {
				return item;
			}
			index ++;
		}
		return null;
	}
	public void remainingItemsMenu(Scanner scanner, Set<FoodItem> remainingItems, int restaurantId) {
		if(!remainingItems.isEmpty()) {
			while(true && !remainingItems.isEmpty()) {
				displayItems(remainingItems);
				System.out.println("Enter Your Choice or press -1 to EXIT: ");
				int choice = scanner.nextInt();
				if(choice == -1) {
					return;
				}
				FoodItem foodItem = getChosenItem(remainingItems, choice);
				Restaurant restaurant = RestaurantManager.getRestaurant(restaurantId);
				System.out.println("Enter The Price: ");
				double price = scanner.nextDouble();
				RestaurantFoodItemManager.addFoodItem(new RestaurantFoodItem(foodItem, restaurant, price, 4));
				remainingItems.remove(foodItem);
			}
		}
		System.out.println("All The Items are added to you Menu!");
	}
	
	
	public void run(Scanner scanner) {
		displayRestaurants();
		System.out.println("Enter Your Choice: ");
		int restaurantChoice = scanner.nextInt();
		int restaurantId = getRestaurantId(restaurantChoice);
		int index, itemChoice;
		List<RestaurantFoodItem> currentItems;
		RestaurantFoodItem selectedItem;
		outerWhile:
		while(true) {
			System.out.println("1. ADD ITEM TO MENU\n2. MODIFY ITEM COST\n3. VIEW CURRENT MENU"
					+ "\n4. REMOVE ITEM FROM MENU\n5. EXIT\nEnter Your Choice: ");
			int choice = scanner.nextInt();
			switch(choice) {
				case 1:
					Set<FoodItem> currentMenuItems = RestaurantMenu.getRestaurantMenuItems(restaurantId);
					Set<FoodItem> allItems = new LinkedHashSet<>(FoodItemsManager.getAllFoodItems());
					allItems.removeAll(currentMenuItems);
					Set<FoodItem> remainingItems = allItems;
					remainingItemsMenu(scanner, remainingItems, restaurantId);
					break;
				case 2:
					currentItems = RestaurantMenu.getRestaurantMenu(restaurantId);
					System.out.println();
					index = 1;
					for (RestaurantFoodItem item : currentItems) {
					    System.out.println(index++ + ". " + item.getItem().getName());
					}
					System.out.println("Enter Your Choice: ");
					itemChoice = scanner.nextInt();
					System.out.println("Enter the new Price: ");
					double newPrice = scanner.nextDouble();
					selectedItem = currentItems.get(itemChoice - 1);
					selectedItem.setPrice(newPrice);
					break;
				case 3:
					displayRestaurantMenu(restaurantId);
					System.out.println();
					System.out.println();
					break;
				case 4:
					currentItems = RestaurantMenu.getRestaurantMenu(restaurantId);
					System.out.println();
					index = 1;
					for (RestaurantFoodItem item : currentItems) {
					    System.out.println(index++ + ". " + item.getItem().getName());
					}
					System.out.println("Enter Your Choice or -1 to break: ");
					itemChoice = scanner.nextInt();
					if(itemChoice == -1)break;
					selectedItem = currentItems.get(itemChoice - 1);
					RestaurantMenu.removeFoodItemFromMenu(restaurantId, selectedItem);
					break;
				case 5:
					break outerWhile;
				default:
					System.out.println("Invalid Choice !");
			}
		}
	}
}
