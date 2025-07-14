package com.aurionpro.usersPanel;

import java.util.LinkedHashSet;
import java.util.Map.Entry;

import com.aurionpro.foodItem.FoodItem;
import com.aurionpro.foodItem.FoodItemsManager;
import com.aurionpro.restaurantFoodItemMapper.RestaurantFoodItem;
import com.aurionpro.restaurantFoodItemMapper.RestaurantFoodItemManager;
import com.aurionpro.restaurants.Restaurant;
import com.aurionpro.restaurants.RestaurantManager;
import com.aurionpro.restaurants.RestaurantMenu;

import java.util.Scanner;
import java.util.Set;

public class PanelRestaurantManager {
	
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
	private void diaplayRemainingItems(Set<FoodItem> remainingItems) {
		int index = 1;
		for(FoodItem item: remainingItems) {
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
				diaplayRemainingItems(remainingItems);
				System.out.println("Enter Your Choice or press -1 to EXIT: ");
				int choice = scanner.nextInt();
				if(choice == -1) {
					break;
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
		outerWhile:
		while(true) {
			System.out.println("1. CHOOSE RESTAURANT\n2.EXIT\nENter Your Choice: ");
			int choice = scanner.nextInt();
			switch(choice) {
				case 1:
					displayRestaurants();
					System.out.println("Enter Your Choice: ");
					int restaurantChoice = scanner.nextInt();
					int restaurantId = getRestaurantId(restaurantChoice);
					Set<FoodItem> currentMenuItems = RestaurantMenu.getRestaurantMenuItems(restaurantId);
					Set<FoodItem> allItems = new LinkedHashSet<>(FoodItemsManager.getAllFoodItems());
					allItems.removeAll(currentMenuItems);
					Set<FoodItem> remainingItems = allItems;
					remainingItemsMenu(scanner, remainingItems, restaurantId);
					break;
				case 2:
					break outerWhile;
				default:
					System.out.println("Invalid Choice !");
			}
		}
	}
}
