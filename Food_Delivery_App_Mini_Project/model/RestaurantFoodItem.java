package com.aurionpro.model;

public class RestaurantFoodItem {
	private FoodItem foodItem;
	private Restaurant restaurant;
	private double price;
	private double rating;
	
	public RestaurantFoodItem(FoodItem foodItem, Restaurant restaurant, double price, double rating) {
		this.foodItem = foodItem;
		this.restaurant = restaurant;
		this.price = price;
		this.rating = rating;
	}

	public FoodItem getItem() {
		return foodItem;
	}

	public void setItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
