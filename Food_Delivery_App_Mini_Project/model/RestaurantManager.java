package com.aurionpro.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RestaurantManager {
	private static List<Restaurant> restaurantList = new ArrayList<>();
	
	public static void addRestaurant(Restaurant restaurant) {
		System.out.println();
		System.out.println();
		if(isValid(restaurant.getName(), restaurant.getAddress())) {
			if(restaurantExists(restaurant.getName())) {
				System.out.println("Restaurant Exists !");
				System.out.println();
				System.out.println();
				return;
			}
			restaurantList.add(restaurant);
			System.out.println("Restaurant Added Successfully");
			System.out.println();
			System.out.println();
			return;
		}
		
		System.out.println("Invalid Details !");
		System.out.println();
		System.out.println();
	}
	
	public static Map<Integer, String> getRestaurants() {
		Map<Integer, String> restaurants = new LinkedHashMap<>();
		for(Restaurant restaurant: restaurantList) {
			restaurants.put(restaurant.getId(), restaurant.getName());
		}
		return restaurants;
	}
	
	public static String getRestaurantNameById(int restaurantId) {
		for(Restaurant restaurant:restaurantList) {
			if(restaurant.getId() == restaurantId) {
				return restaurant.getName();
			}
		}
		return null;
	}
	
	private static boolean restaurantExists(String restaurantName) {
		for(Restaurant restaurant:restaurantList) {
			if(restaurantName.equalsIgnoreCase(restaurant.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public static Restaurant getRestaurant(int restaurantId) {
		for(Restaurant restaurant:restaurantList) {
			if(restaurant.getId() == restaurantId) {
				return restaurant;
			}
		}
		return null;
	}
	
	public static List<Restaurant> getAllRestaurants(){
		return restaurantList;
	}
	
	public static boolean isValid(String name, String address) {
        return isNotBlank(name) && isNotBlank(address);
    }

    private static boolean isNotBlank(String input) {
        return input != null && !input.trim().isEmpty();
    }
}
