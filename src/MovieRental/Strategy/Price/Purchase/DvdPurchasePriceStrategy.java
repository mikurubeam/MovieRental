package MovieRental.Strategy.Price.Purchase;

import MovieRental.Strategy.Price.PriceStrategy;
import MovieRental.Strategy.Price.PriceStrategyDecorator;

public class DvdPurchasePriceStrategy extends PriceStrategyDecorator {
    public DvdPurchasePriceStrategy(PriceStrategy strategy) {
        super(strategy);
    }

    @Override
    public double getPrice() {
        return this.strategy.getPrice() + 5;
    }
}
