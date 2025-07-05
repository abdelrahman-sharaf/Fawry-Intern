package products;
import interfaces.Shippable;

public class ShippableProduct extends Product implements Shippable{
    private double weight;
    
    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public boolean isExpired() {
        return false;
    }
    
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }
}