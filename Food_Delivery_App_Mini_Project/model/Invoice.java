package com.aurionpro.model;

public class Invoice {
	
	public static void printInvoice(Order order) {
        System.out.println("========== INVOICE ==========");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Date/Time: " + order.getTimestamp());
        System.out.println("Customer ID: " + order.getCustomerId());
        System.out.println("Restaurant: " + order.getRestaurantName() + " (ID: " + order.getRestaurantId() + ")");
        System.out.println();
        System.out.println("Items:");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-4s %-22s %-10s %-12s %-12s%n", "No.", "Item Name", "Quantity", "Unit Price", "Total Price");
        System.out.println("----------------------------------------------------------------------------------");

        int index = 1;
        for (OrderItem orderItem : order.getItems()) {
            FoodItem food = orderItem.getItem();
            System.out.printf(
                "%-4d %-22s %-10d ₹%-11.2f ₹%-11.2f%n",
                index++,
                food.getName(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getTotalPrice()
            );
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("Subtotal:%58s₹%.2f%n", "", order.getTotalPrice());
        System.out.printf("Final Price (with charges/discounts):%25s₹%.2f%n", "", order.getFinalPrice());
        System.out.println();
        System.out.println("Instructions: " + order.getInstruction());
        System.out.println("=============================");
    }
}
