package products;
import interfaces.Shippable;
import java.time.LocalDate;

public class PerishableProduct extends Product implements Shippable{
    private LocalDate expirationDate;
    private double weight;
    
    public PerishableProduct(String name,double price,int quantity,LocalDate expirationDate,double weight){
        super(name,price,quantity);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    public boolean isExpired(){
        return LocalDate.now().isAfter(expirationDate);
    }
    public boolean requiresShipping(){
        return true;
    }
    public double getWeight(){
        return weight;
    }

}