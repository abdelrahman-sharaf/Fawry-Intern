import customer.Customer;
import system.ECommerceSystem;
import products.*;
import cart.Cart;
import java.time.LocalDate;

public class ECommerceMain {
    public static void main(String[] args) {
        // Create products
        Product cheese = new PerishableProduct("Cheese", 100, 10, LocalDate.now().plusDays(7), 0.2);
        Product biscuits = new PerishableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(30), 0.7);
        Product tv = new ShippableProduct("TV", 5000, 3, 15.0);
        //Product mobile = new ShippableProduct("Mobile", 8000, 2, 0.5);
        Product scratchCard = new DigitalProduct("Mobile Scratch Card", 50, 100);
        
        // Create customer
        Customer customer = new Customer("John Doe", 1000);
        
        // Test Case 1: Successful checkout with mixed products
        System.out.println("=== Test Case 1: Successful Checkout ===");
        Cart cart1 = new Cart();
        cart1.add(cheese, 2);
        cart1.add(biscuits, 1);
        cart1.add(scratchCard, 1);
        
        ECommerceSystem.checkout(customer, cart1);
        
        // Test Case 2: Empty cart
        System.out.println("\n=== Test Case 2: Empty Cart ===");
        Cart cart2 = new Cart();
        ECommerceSystem.checkout(customer, cart2);
        
        // Test Case 3: Insufficient balance
        System.out.println("\n=== Test Case 3: Insufficient Balance ===");
        Customer poorCustomer = new Customer("Jane Doe", 100);
        Cart cart3 = new Cart();
        cart3.add(tv, 1);
        ECommerceSystem.checkout(poorCustomer, cart3);
        
        // Test Case 4: Out of stock
        System.out.println("\n=== Test Case 4: Out of Stock ===");
        Cart cart4 = new Cart();
        try {
            cart4.add(tv, 5); // Only 3 TVs available
        } catch (Exception e) {
            System.err.println("Add to cart failed: " + e.getMessage());
        }
        
        // Test Case 5: Expired product
        System.out.println("\n=== Test Case 5: Expired Product ===");
        Product expiredCheese = new PerishableProduct("Expired Cheese", 100, 5, LocalDate.now().minusDays(1), 0.2);
        Cart cart5 = new Cart();
        cart5.add(expiredCheese, 1);
        ECommerceSystem.checkout(customer, cart5);
        
        // Test Case 6: Digital product only (no shipping)
        System.out.println("\n=== Test Case 6: Digital Product Only ===");
        Cart cart6 = new Cart();
        cart6.add(scratchCard, 3);
        ECommerceSystem.checkout(customer, cart6);
    }
}