package MovieRental.Strategy.Price;
import MovieRental.Item.Item;
import MovieRental.Customer.Customer;

public class PriceStrategyDecorator implements PriceStrategy {
    private PriceStrategy strategy;

    public PriceStrategyDecorator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double getPrice() {
        return this.strategy.getPrice();
    }

    @Override
    public Item getItem() {
        return this.strategy.getItem();
    }

    @Override
    public Customer getCustomer() {
        return this.strategy.getCustomer();
    }
}
