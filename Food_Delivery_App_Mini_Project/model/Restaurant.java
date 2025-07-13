package com.aurionpro.model;

public class Restaurant {
	private final int id;
	private String name;
	private String address;
	private static int idCount = 1;
	
	public Restaurant(String name, String address) {
		id = idCount;
		this.name = name;
		this.address = address;
		idCount++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}
}
