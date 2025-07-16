package com.aurionpro.usersPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aurionpro.cart.Cart;
import com.aurionpro.foodItem.FoodCategory;
import com.aurionpro.foodItem.FoodItem;
import com.aurionpro.invoice.Invoice;
import com.aurionpro.order.Order;
import com.aurionpro.order.OrderItem;
import com.aurionpro.order.OrderManager;
import com.aurionpro.payment.Card;
import com.aurionpro.payment.Cash;
import com.aurionpro.payment.UPI;
import com.aurionpro.restaurantFoodItemMapper.RestaurantFoodItem;
import com.aurionpro.restaurantFoodItemMapper.RestaurantFoodItemManager;
import com.aurionpro.restaurants.RestaurantManager;
import com.aurionpro.restaurants.RestaurantMenu;
import com.aurionpro.user.CustomersManager;

import java.util.Scanner;

public class PanelCustomer {
	private static int currentCustomerId;
	private int currentRestaurantId;
	private Map<Integer, RestaurantFoodItem> indexToItemMapperForMenu;
	
	public int getcustomerId(Map<Integer, String> customers, int choice) {
		int index = 1;
		for(Entry<Integer, String> entry: customers.entrySet()) {
			if(index == choice) {
				return entry.getKey();
			}
			index ++;
		}
		return -1;
	}
	
	public void displayAllCustomers(Map<Integer, String> customers) {
		System.out.println();
		System.out.println();
		int index = 1;
		for(Entry<Integer, String> entry: customers.entrySet()) {
			System.out.println(index+". "+entry.getValue());
			index ++;
		}
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
	
	public void displayRestaurantMenu() {
	    System.out.println();
	    System.out.println();

	    List<RestaurantFoodItem> menu = RestaurantMenu.getRestaurantMenu(currentRestaurantId);
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
	        
	        indexToItemMapperForMenu = new HashMap<>();
	        for (Map.Entry<FoodCategory, List<RestaurantFoodItem>> entry : categorizedMenu.entrySet()) {
	            FoodCategory category = entry.getKey();
	            List<RestaurantFoodItem> items = entry.getValue();

	            System.out.println();
	            System.out.println(category.name() + " ITEMS");
	            System.out.println("------------------------------");
	            System.out.printf("%-4s %-25s %-10s%n", "No.", "Item Name", "Price");

	            for (RestaurantFoodItem foodItem:items) {
	            	indexToItemMapperForMenu.put(idCount, foodItem);
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

	
	public RestaurantFoodItem getfoodItem(int foodItemChoice) {
		return indexToItemMapperForMenu.get(foodItemChoice);
	}
	
	public int getCartItemID(int choice, Cart cart) {
		
		List<Integer> cartItemId = cart.cartItemIdList();
		return cartItemId.get(choice - 1);
		
	}
	
	public void manageCart(Scanner scanner, Cart cart) {
		System.out.println();
		System.out.println();
		int itemId, quantity;
		System.out.println("--------- ORDERING SECTION ---------");
		System.out.println();
		outerWhile:
		while(true) {
			System.out.println("1. ADD ITEM TO CART\n2. REMOVE ITEM FROM CART\n3. MODIFY QUANTITY\n4. VIEW CART\n5. CHECKOUT"
					+"\nENter your choice: ");
			int choice = scanner.nextInt();
			switch(choice) {
				case 1:
					displayRestaurantMenu();
					if(RestaurantMenu.getRestaurantMenu(currentRestaurantId) != null) {
						System.out.println();
						System.out.println("Enter Your Choice: ");
						int foodItemChoice = scanner.nextInt();
						RestaurantFoodItem foodItem = getfoodItem(foodItemChoice);
						System.out.println("Enter Quantity: ");
						quantity = scanner.nextInt();
						cart.addItem(new OrderItem(foodItem.getItem(), quantity, foodItem.getPrice()));
					}
					break;
				case 2:
					if(!cart.isEmpty()) {
						cart.displayCart();
						System.out.println();
						System.out.println("Enter Item To Remove: ");
						int itemToRemove = scanner.nextInt();
						itemId = getCartItemID(itemToRemove, cart);
						cart.removeItem(itemId);
						break;
					}
					System.out.println();
					System.out.println();
					System.out.println("Cart is Empty");
					System.out.println();
					System.out.println();
					break;
				case 3:
					if(!cart.isEmpty()) {
						cart.displayCart();
						System.out.println();
						System.out.println("Enter Item To Modify: ");
						int itemToModify = scanner.nextInt();
						itemId = getCartItemID(itemToModify, cart);
						System.out.println("Enter the New Quantity: ");
						quantity = scanner.nextInt();
						cart.modifyQuantity(itemId, quantity);
						break;
					}
					System.out.println();
					System.out.println();
					System.out.println("Cart is Empty");
					System.out.println();
					System.out.println();
					break;
				case 4:
					cart.displayCart();
					break;
				case 5:
					if(cart.isEmpty()) {
						break outerWhile;
					}
					scanner.nextLine();
					System.out.println("Enter any Instruction To Add: ");
					String instruction = scanner.nextLine();
					Order order = new Order(cart.getCartItems(), currentRestaurantId, currentCustomerId, RestaurantManager.getRestaurantNameById(currentRestaurantId), instruction);
					OrderManager.addOrder(order);
					System.out.println("PAYMENT METHODS");
					System.out.println("------------------");
					System.out.println("1. UPI\n2. CASH\n3.CARD\nENter Your Choice");
					int paymentChoice = scanner.nextInt();
					if(paymentChoice == 1) {
						UPI upi = new UPI();
						upi.pay(scanner, order.getFinalPrice(), order.getId());
					}
					if(paymentChoice == 2) {
						Cash cash = new Cash();
						cash.pay(scanner, order.getFinalPrice(), order.getId());
					}
					if(paymentChoice == 3) {
						Card card = new Card();
						card.pay(scanner, order.getFinalPrice(), order.getId());
					}
					System.out.println();
					System.out.println();
					Invoice.printInvoice(order);
					System.out.println();
					System.out.println();
					break outerWhile;
				default:
					System.out.println();
					System.out.println();
					System.out.println("Invalid Choice");
					System.out.println();
					System.out.println();
			}
		}
		
	}
	
	public static void displayFoodByCategory(FoodCategory category) {
	    List<RestaurantFoodItem> items = RestaurantFoodItemManager.getFoodOfCategory(category);
	    System.out.println("\n\n");

	    if (items == null || items.isEmpty()) {
	        System.out.println("No food items found in category: " + category);
	        System.out.println("\n\n");
	        return;
	    }

	    int nameWidth = "Food Name".length();
	    int descWidth = "Description".length();
	    int restWidth = "Restaurant".length();

	    for (RestaurantFoodItem item : items) {
	        FoodItem food = item.getItem();
	        nameWidth = Math.max(nameWidth, food.getName().length());
	        descWidth = Math.max(descWidth, food.getDescription().length());
	        restWidth = Math.max(restWidth, item.getRestaurant().getName().length());
	    }

	    String format = String.format("%%-4s %%-%ds %%-%ds %%-%ds %%10s %%10s%n", nameWidth, descWidth, restWidth);
	    String rowFormat = String.format("%%-4d %%-%ds %%-%ds %%-%ds ₹%%-9.2f %%10.1f%n", nameWidth, descWidth, restWidth);

	    System.out.println("=========== " + category + " MENU ===========");
	    System.out.printf(format, "No.", "Food Name", "Description", "Restaurant", "Price", "Rating");

	    int totalWidth = 4 + 1 + nameWidth + 1 + descWidth + 1 + restWidth + 1 + 10 + 1 + 10 + 6;
	    System.out.println("-".repeat(totalWidth));

	    int index = 1;
	    for (RestaurantFoodItem item : items) {
	        FoodItem food = item.getItem();
	        System.out.printf(
	            rowFormat,
	            index++,
	            food.getName(),
	            food.getDescription(),
	            item.getRestaurant().getName(),
	            item.getPrice(),
	            item.getRating()
	        );
	    }

	    System.out.println("-".repeat(totalWidth));
	    System.out.println("\n\n");
	}


	
	public void order(Scanner scanner) {
		Cart cart = new Cart();
		System.out.println();
		System.out.println();
		System.out.println("1. VIEW RESTAURANT MENU & ORDER\n2.VIEW FOOD BY CATEGORY\n3.EXIT\nENter Your Choice:");
		int choice = scanner.nextInt();
		switch(choice) {
			case 1:
				displayRestaurants();
				System.out.println("Enter Your choice: ");
				int restaurantChoice = scanner.nextInt();
				currentRestaurantId = getRestaurantId(restaurantChoice);
				manageCart(scanner, cart);
				break;
			case 2:
				while(true) {
					System.out.println("Categories Available:\n1. Indian\n2. Italian\n3. Chinese\nENter Your choice or 4 for EXIT: ");
					int categoryChoice = scanner.nextInt();
					if(categoryChoice == 4) {
						break;
					}
					FoodCategory category = null;
					if(categoryChoice == 1) {
						category = FoodCategory.INDIAN;
					}
					if(categoryChoice == 2) {
						category = FoodCategory.ITALIAN;
					}
					if(categoryChoice == 3) {
						category = FoodCategory.CHINESE;
					}
					displayFoodByCategory(category);
				}
				break;
			case 3:
				break;
			default:
				System.out.println();
				System.out.println();
				System.out.println("Invalid Choice");
				System.out.println();
				System.out.println();
		}
	}
	
	public static void displayOrdersOfCustomer(List<Order> orders) {
		
		System.out.println();
		System.out.println();
	    if (orders == null || orders.isEmpty()) {
	        System.out.println("You have no previous orders.");
	        return;
	    }

	    System.out.println("========== Your Previous Orders ==========");
	    System.out.printf("%-4s %-10s %-25s %-20s %-10s%n", "No.", "Order ID", "Restaurant", "Date/Time", "Final Price");
	    System.out.println("---------------------------------------------------------------");

	    int index = 1;
	    for (Order order : orders) {
	        System.out.printf(
	            "%-4d %-10d %-25s %-20s ₹%-9.2f%n",
	            index++,
	            order.getId(),
	            order.getRestaurantName(),
	            order.getTimestamp(),
	            order.getFinalPrice()
	        );
	    }

	    System.out.println("===========================================");
	    System.out.println();
	    System.out.println();
	}
	
	public void customerMainPanel(Scanner scanner) {
		while(true) {
			System.out.println();
			System.out.println();
			System.out.println("1. ADD AN ORDER\n2. VIEW PREVIOUS ORDERS\n3. EXIT\nEnter Your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 1) {
				order(scanner);
			}
			if(choice == 2) {
				List<Order> customerOrders = OrderManager.getAllOrdersOfCustomer(currentCustomerId);
				if(customerOrders != null) {
					while(true) {
						displayOrdersOfCustomer(customerOrders);
						System.out.println("Enter Your Choice & To exit enter -1: ");
						int orderChoice = scanner.nextInt();
						if(orderChoice == -1){
							break;
						}
						System.out.println();
						System.out.println();
						Invoice.printInvoice(customerOrders.get(orderChoice - 1));
						System.out.println();
						System.out.println();
					}
				}
				else {
					System.out.println("No Orders Placed");
				}
			}
			if(choice == 3) {
				break;
			}
		}
	}
	
	public void run(Scanner scanner) {
		System.out.println();
		System.out.println();
		System.out.println("WELCOME TO OUR APP");
		while(true) {
			System.out.println("1. CHOOSE AN EXISTING CUSTOMER\n2. ADD A NEW CUSTOMER\n3. EXIT\nEnter Your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 1) {
				Map<Integer, String> customers = CustomersManager.getAllCustomers();
				System.out.println();
				displayAllCustomers(customers);
				System.out.println("Enter Your Choice: ");
				int customerChoice = scanner.nextInt();
				currentCustomerId = getcustomerId(customers, customerChoice);
				if(currentCustomerId == -1) {
					System.out.println("Invalid Choice");
					continue;
				}
				customerMainPanel(scanner);
				continue;
			}
			if(choice == 2) {
				scanner.nextLine();
				CustomersManager.addCustomer(scanner);
				continue;
			}
			if(choice == 3) {
				return;
			}
			System.out.println("Invalid Choice");
		}
	}
}
