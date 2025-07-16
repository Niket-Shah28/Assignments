package com.aurionpro.restaurantFoodItemMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aurionpro.foodItem.FoodCategory;
import com.aurionpro.foodItem.FoodItem;
import com.aurionpro.restaurants.RestaurantMenu;

public class RestaurantFoodItemManager {
	private static Map<FoodCategory, List<RestaurantFoodItem>> restaurantFoodItem = new HashMap<>();
	
	public static List<RestaurantFoodItem> getFoodOfCategory(FoodCategory foodCategory){
		return restaurantFoodItem.get(foodCategory);
	}
	
	public static void addFoodItem(RestaurantFoodItem item) {
	    FoodItem foodItem = item.getItem();
	    FoodCategory category = foodItem.getCategory();

	    if (!restaurantFoodItem.containsKey(category)) {
	        List<RestaurantFoodItem> newList = new ArrayList<>();
	        newList.add(item);
	        restaurantFoodItem.put(category, newList);
	    } else {
	        List<RestaurantFoodItem> currItemsList = restaurantFoodItem.get(category);
	        currItemsList.add(item);
	        restaurantFoodItem.put(category, currItemsList);
	    }

	    RestaurantMenu.addItemToMenu(item); 
	    System.out.println();
		System.out.println();
	    System.out.println("Item Added Successfully");
	    System.out.println();
		System.out.println();
	}

}
