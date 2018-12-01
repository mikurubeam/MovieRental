package MovieRental.Strategy.Price.Purchase;

import MovieRental.Customer.Customer;
import MovieRental.Item.Item;
import MovieRental.Strategy.Price.PriceStrategy;

public class PurchasePriceStrategy implements PriceStrategy {
    protected double basePrice;
    protected double newReleasePremium;
    private Item item;

    public PurchasePriceStrategy(Item item) {
        this.basePrice = 0;
        this.newReleasePremium = 0;
        this.item = item;
    }

    public double getPrice() {
        return this.basePrice;
    }

    public Item getItem() {
        return this.item;
    }

    public Customer getCustomer() {
        return this.item.getCustomer();
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getNewReleasePremium() {
        return this.newReleasePremium;
    }
}
