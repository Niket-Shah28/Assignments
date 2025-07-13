package com.aurionpro.model;

public class FoodItem {
	private final int id;
	private String name;
	private String description;
	private FoodCategory category;
	private String ingredients;
	private static int idCount = 1;
	
	public FoodItem(String name, String description, FoodCategory category, String ingredients) {
		this.id = idCount;
		this.name = name;
		this.description = description;
		this.category = category;
		this.ingredients = ingredients;
		idCount++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FoodCategory getCategory() {
		return category;
	}

	public void setCategory(FoodCategory category) {
		this.category = category;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public int getId() {
		return id;
	}
}
