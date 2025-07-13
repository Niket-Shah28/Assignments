package com.aurionpro.test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import com.aurionpro.model.CustomersManager;
import com.aurionpro.model.DeliveryPartnerManager;
import com.aurionpro.model.FoodCategory;
import com.aurionpro.model.FoodItem;
import com.aurionpro.model.FoodItemsManager;
import com.aurionpro.model.PanelAdmin;
import com.aurionpro.model.PanelCustomer;
import com.aurionpro.model.PanelRestaurantManager;
import com.aurionpro.model.Restaurant;
import com.aurionpro.model.RestaurantFoodItem;
import com.aurionpro.model.RestaurantFoodItemManager;
import com.aurionpro.model.RestaurantManager;

public class FoodDeliveryAppTest {

	public static void main(String[] args) {
		loadStubs();
		Scanner scanner = new Scanner(System.in);
		PanelAdmin admin= new PanelAdmin();
		PanelCustomer customer = new PanelCustomer();
		PanelRestaurantManager restaurantManager = new PanelRestaurantManager();
		outerWhile:
		while(true) {
			System.out.println("1. ADMIN PANEL\n2. CUSTOMER PANEL\n3. RESTAURANT MANAGER PANEL\n4. EXIT\nENTER YOUR CHOICE: ");
			int choice = scanner.nextInt();
			switch(choice) {
				case 1:
					admin.run(scanner);
					break;
				case 2:
					customer.run(scanner);
					break;
				case 3:
					restaurantManager.run(scanner);
					break;
				case 4:
					break outerWhile;
				default:
					System.out.println();
					System.out.println();
					System.out.println("INVALID CHOICE !");
					System.out.println();
					System.out.println();
			}		
		}
	}
	
	public static void loadStubs() {
		CustomersManager.addCustomerDirect("Arjun Malhotra", "9123456780", "arjun.m@example.com", "Delhi", "arjun@123");
        CustomersManager.addCustomerDirect("Sneha Patil", "9123456781", "sneha.p@example.com", "Mumbai", "sneha@123");
        CustomersManager.addCustomerDirect("Karan Mehra", "9123456782", "karan.m@example.com", "Pune", "karan@123");
        CustomersManager.addCustomerDirect("Riya Kapoor", "9123456783", "riya.k@example.com", "Chennai", "riya@123");
        CustomersManager.addCustomerDirect("Abhishek Sen", "9123456784", "abhishek.s@example.com", "Kolkata", "abhi@123");
        CustomersManager.addCustomerDirect("Priya Desai", "9123456785", "priya.d@example.com", "Bangalore", "priya@123");
        
        
		RestaurantManager.addRestaurant(new Restaurant("Tandoori Nights", "123 Spice St, Delhi"));
        RestaurantManager.addRestaurant(new Restaurant("Pizza Mania", "88 Cheese Ave, Mumbai"));
        RestaurantManager.addRestaurant(new Restaurant("Spicy Hub", "456 Curry Rd, Hyderabad"));
        RestaurantManager.addRestaurant(new Restaurant("Burger Junction", "789 Grill Ln, Bengaluru"));
        RestaurantManager.addRestaurant(new Restaurant("Noodle Town", "12 Soy Blvd, Kolkata"));
        RestaurantManager.addRestaurant(new Restaurant("Biryani Express", "34 Masala Marg, Lucknow"));
        RestaurantManager.addRestaurant(new Restaurant("Chaat House", "19 Tangy Rd, Jaipur"));
        RestaurantManager.addRestaurant(new Restaurant("South Flavors", "77 Idli St, Chennai"));
        RestaurantManager.addRestaurant(new Restaurant("Kebab Corner", "23 Skewer Path, Pune"));
        RestaurantManager.addRestaurant(new Restaurant("Wrap & Roll", "65 Street Food Sq, Ahmedabad"));
        
        
        FoodItemsManager.addFoodItem(new FoodItem("Chicken Biryani", "Spiced rice with chicken", FoodCategory.INDIAN, "Chicken, Rice, Spices"));
        FoodItemsManager.addFoodItem(new FoodItem("Paneer Butter Masala", "Paneer in tomato gravy", FoodCategory.INDIAN, "Paneer, Tomato, Cream, Spices"));
        FoodItemsManager.addFoodItem(new FoodItem("Masala Dosa", "Crispy dosa with potato filling", FoodCategory.INDIAN, "Rice, Lentils, Potato, Mustard Seeds"));
        FoodItemsManager.addFoodItem(new FoodItem("Veg Manchurian", "Fried veggie balls in sauce", FoodCategory.CHINESE, "Cabbage, Carrot, Flour, Soy Sauce"));
        FoodItemsManager.addFoodItem(new FoodItem("Butter Naan", "Soft flatbread with butter", FoodCategory.INDIAN, "Maida, Butter, Curd"));
        FoodItemsManager.addFoodItem(new FoodItem("Mango Lassi", "Chilled mango yogurt drink", FoodCategory.INDIAN, "Mango, Yogurt, Sugar"));
        FoodItemsManager.addFoodItem(new FoodItem("Gulab Jamun", "Sweet syrupy dessert", FoodCategory.INDIAN, "Milk Powder, Sugar, Cardamom"));
        FoodItemsManager.addFoodItem(new FoodItem("Hakka Noodles", "Stir-fried noodles with veggies", FoodCategory.CHINESE, "Noodles, Capsicum, Soy Sauce"));
        FoodItemsManager.addFoodItem(new FoodItem("Tandoori Chicken", "Grilled chicken in spices", FoodCategory.INDIAN, "Chicken, Yogurt, Tandoori Masala"));
        FoodItemsManager.addFoodItem(new FoodItem("Margherita Pizza", "Classic cheese pizza", FoodCategory.ITALIAN, "Flour, Cheese, Tomato Sauce"));
	
        List<Restaurant> restaurants = RestaurantManager.getAllRestaurants();
        List<FoodItem> foodItems = FoodItemsManager.getAllFoodItems();

        Random random = new Random();
        Set<String> assignedPairs = new HashSet<>();

        for (Restaurant restaurant : restaurants) {
            int numberOfItems = 5 + random.nextInt(3);
            Set<Integer> selectedIndexes = new HashSet<>();

            while (selectedIndexes.size() < numberOfItems) {
                int foodIndex = random.nextInt(foodItems.size());
                if (selectedIndexes.contains(foodIndex)) continue;

                selectedIndexes.add(foodIndex);
                FoodItem foodItem = foodItems.get(foodIndex);

                String key = restaurant.getId() + "-" + foodItem.getId();
                if (assignedPairs.contains(key)) continue;

                assignedPairs.add(key);
                double price = 100 + random.nextInt(301);
                double rating = 3.5 + random.nextDouble() * 1.5;

                RestaurantFoodItem rfi = new RestaurantFoodItem(foodItem, restaurant, price, rating);
                RestaurantFoodItemManager.addFoodItem(rfi);
            }
        }
        
        DeliveryPartnerManager.addDeliveryPartner("Ravi Kumar", "9876543210", "ravi.kumar@example.com", "Delhi", "pass123");
        DeliveryPartnerManager.addDeliveryPartner("Amit Sharma", "9876543211", "amit.sharma@example.com", "Mumbai", "pass124");
        DeliveryPartnerManager.addDeliveryPartner("Pooja Mehta", "9876543212", "pooja.mehta@example.com", "Bangalore", "pass125");
        DeliveryPartnerManager.addDeliveryPartner("Rahul Verma", "9876543213", "rahul.verma@example.com", "Hyderabad", "pass126");
        DeliveryPartnerManager.addDeliveryPartner("Neha Singh", "9876543214", "neha.singh@example.com", "Kolkata", "pass127");
        DeliveryPartnerManager.addDeliveryPartner("Suresh Yadav", "9876543215", "suresh.yadav@example.com", "Chennai", "pass128");
        DeliveryPartnerManager.addDeliveryPartner("Anita Joshi", "9876543216", "anita.joshi@example.com", "Pune", "pass129");
        DeliveryPartnerManager.addDeliveryPartner("Vikram Das", "9876543217", "vikram.das@example.com", "Ahmedabad", "pass130");
        DeliveryPartnerManager.addDeliveryPartner("Deepika Rao", "9876543218", "deepika.rao@example.com", "Jaipur", "pass131");
        DeliveryPartnerManager.addDeliveryPartner("Manoj Nair", "9876543219", "manoj.nair@example.com", "Lucknow", "pass132");
        
        System.out.println();
		System.out.println();
	}

}
