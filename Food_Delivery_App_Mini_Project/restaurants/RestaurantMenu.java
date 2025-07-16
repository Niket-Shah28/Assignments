package com.aurionpro.restaurants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aurionpro.foodItem.FoodItem;
import com.aurionpro.restaurantFoodItemMapper.RestaurantFoodItem;

public class RestaurantMenu {
	private static Map<Integer, List<RestaurantFoodItem>> restaurantMenus = new HashMap<>();
	
	public static List<RestaurantFoodItem> getRestaurantMenu(int restaurantId){
		return restaurantMenus.get(restaurantId);
	}
	
	public static void addItemToMenu(RestaurantFoodItem item) {
	    Restaurant restaurant = item.getRestaurant();
	    List<RestaurantFoodItem> currentMenu;

	    if (restaurantMenus.containsKey(restaurant.getId())) {
	        currentMenu = restaurantMenus.get(restaurant.getId());
	    } else {
	        currentMenu = new ArrayList<>();
	    }
	    currentMenu.add(item);
	    restaurantMenus.put(restaurant.getId(), currentMenu);
	    System.out.println();
		System.out.println();
	    System.out.println("Restaurant Menu Updated Successfully");
	    System.out.println();
		System.out.println();
	}
	
	public static Set<FoodItem> getRestaurantMenuItems(int restaurantId){
		List<RestaurantFoodItem> menu = restaurantMenus.get(restaurantId);
		Set<FoodItem> menuItems = new LinkedHashSet<>();
		if(menu != null) {
			for(RestaurantFoodItem item: menu) {
				menuItems.add(item.getItem());
			}
			return menuItems;
		}
		return new LinkedHashSet<>();
	}
	
	public static void removeFoodItemFromMenu(int restaurantId, RestaurantFoodItem itemToBeRemoved) {
		List<RestaurantFoodItem> menu = restaurantMenus.get(restaurantId);
		menu.remove(itemToBeRemoved);
		System.out.println("Item Removed Successfully!!");
	}

}
