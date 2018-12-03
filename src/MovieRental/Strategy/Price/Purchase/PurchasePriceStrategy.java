package MovieRental.Strategy.Price.Purchase;

import MovieRental.Customer.Customer;
import MovieRental.Item.Item;
import MovieRental.Strategy.Price.PriceStrategy;

public class PurchasePriceStrategy implements PriceStrategy {
    private Item item;

    public PurchasePriceStrategy(Item item) {
        this.item = item;
    }

    public double getPrice() {
        return this.item.getBasePurchasePrice();
    }

    public Item getItem() {
        return this.item;
    }

    public Customer getCustomer() {
        return this.item.getCustomer();
    }
}
