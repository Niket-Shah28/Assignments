package com.aurionpro.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FoodItemsManager {
    private static List<FoodItem> foodItemList = new ArrayList<>();

    public static void addFoodItem(FoodItem foodItem) {
    	if(isValid(foodItem.getName(), foodItem.getDescription(), foodItem.getIngredients())) {
	    	if(foodItemExists(foodItem)) {
	    		System.out.println("Food Item Already Exists");
	    		return;
	    	}
	    	foodItemList.add(foodItem);
	        System.out.println("Food Item Added Successfully");
	        return;
    	}
    	System.out.println("Invalid Details !");
    }

    public static Map<Integer, String> getFoodItems() {
        Map<Integer, String> foodItems = new LinkedHashMap<>();
        for (FoodItem foodItem : foodItemList) {
            foodItems.put(foodItem.getId(), foodItem.getName());
        }
        return foodItems;
    }

    public static String getFoodItemNameById(int foodItemId) {
        for (FoodItem foodItem : foodItemList) {
            if (foodItem.getId() == foodItemId) {
                return foodItem.getName();
            }
        }
        return null;
    }

    public static List<FoodItem> getAllFoodItems() {
        return foodItemList;
    }
    
    public static Set<String> getFoodItemsName() {
    	Set<String> foodItems = new LinkedHashSet<>();
        for (FoodItem foodItem : foodItemList) {
        	foodItems.add(foodItem.getName());
        }
        return foodItems;
    }
    
    private static boolean foodItemExists(FoodItem newFoodItem) {
    	String newItemName = newFoodItem.getName();
    	for(FoodItem existingFoodItem: foodItemList) {
    		String existingItemName = existingFoodItem.getName();
    		if(existingItemName.equalsIgnoreCase(newItemName)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static boolean isValid(String name, String description, String ingredients) {
        return isNotBlank(name) && isNotBlank(description) && isNotBlank(ingredients);
    }

    private static boolean isNotBlank(String input) {
        return input != null && !input.trim().isEmpty();
    }
}

