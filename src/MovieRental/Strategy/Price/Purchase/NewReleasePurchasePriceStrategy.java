package MovieRental.Strategy.Price.Purchase;

import MovieRental.Strategy.Price.PriceStrategy;
import MovieRental.Strategy.Price.PriceStrategyDecorator;

public class NewReleasePurchasePriceStrategy extends PriceStrategyDecorator {
    public NewReleasePurchasePriceStrategy(PriceStrategy strategy) {
        super(strategy);
    }

    @Override
    public double getPrice() {
        PurchasePriceStrategy strategy = (PurchasePriceStrategy) this.strategy;

        return strategy.getBasePrice() * strategy.getNewReleasePremium();
    }
}
