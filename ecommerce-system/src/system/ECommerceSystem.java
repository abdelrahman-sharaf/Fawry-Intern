package system;
import customer.Customer;
import cart.Cart;
import cart.CartItem;
import products.Product;
import interfaces.Shippable;
import services.ShippingService;
import java.util.List;


public class ECommerceSystem {
    
    public static void checkout(Customer customer, Cart cart) {
        try {
            // Validate cart is not empty
            if (cart.isEmpty()) {
                throw new RuntimeException("Cart is empty");
            }
            
            // Validate products availability and expiration
            for (CartItem item : cart.getItems()) {
                Product product = item.getProduct();
                
                if (product.isExpired()) {
                    throw new RuntimeException("Product " + product.getName() + " is expired");
                }
                
                if (item.getQuantity() > product.getQuantity()) {
                    throw new RuntimeException("Product " + product.getName() + " is out of stock");
                }
            }
            
            // Calculate costs
            double subtotal = cart.getSubtotal();
            double shippingFee = 0;
            
            List<Shippable> shippableItems = cart.getShippableItems();
            if (!shippableItems.isEmpty()) {
                shippingFee = ShippingService.calculateShippingFee(shippableItems);
            }
            
            double totalAmount = subtotal + shippingFee;
            
            // Validate customer balance
            if (!customer.canAfford(totalAmount)) {
                throw new RuntimeException("Customer's balance is insufficient");
            }
            
            // Process payment
            customer.deductBalance(totalAmount);
            
            // Update product quantities
            for (CartItem item : cart.getItems()) {
                Product product = item.getProduct();
                product.setQuantity(product.getQuantity() - item.getQuantity());
            }
            
            // Print checkout receipt
            System.out.println("** Checkout receipt **");
            for (CartItem item : cart.getItems()) {
                System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + (int)item.getTotalPrice());
            }
            System.out.println("----------------------");
            System.out.println("Subtotal " + (int)subtotal);
            System.out.println("Shipping " + (int)shippingFee);
            System.out.println("Amount " + (int)totalAmount);
            System.out.println("Customer balance after payment: " + customer.getBalance());
            System.out.println("END.");
            
        } catch (Exception e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }
}