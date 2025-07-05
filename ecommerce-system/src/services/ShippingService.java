package services;
import interfaces.Shippable;
import java.util.List;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 15.0;
    
    public static double calculateShippingFee(List<Shippable> items) {
        double totalWeight = 0;
        
        System.out.println("** Shipment notice **");
        for (Shippable item : items) {
            System.out.println("1x " + item.getName() + " " + (item.getWeight() * 1000) + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + totalWeight + "kg");
        
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
}