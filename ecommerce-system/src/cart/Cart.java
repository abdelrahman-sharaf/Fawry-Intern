package cart;
import products.Product;
import interfaces.Shippable;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock");
        }
        
        // Check if product already exists in cart
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                int newQuantity = item.getQuantity() + quantity;
                if (newQuantity > product.getQuantity()) {
                    throw new IllegalArgumentException("Total quantity in cart exceeds available stock");
                }
                item.setQuantity(newQuantity);
                return;
            }
        }
        
        items.add(new CartItem(product, quantity));
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
    
    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().requiresShipping()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) item.getProduct());
                }
            }
        }
        return shippableItems;
    }
}
