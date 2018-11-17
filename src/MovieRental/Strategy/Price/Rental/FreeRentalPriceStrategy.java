package MovieRental.Strategy.Price.Rental;

import MovieRental.Strategy.Price.PriceStrategy;
import MovieRental.Strategy.Price.PriceStrategyDecorator;

public class FreeRentalPriceStrategy extends PriceStrategyDecorator {
    public FreeRentalPriceStrategy(PriceStrategy strategy) {
        super(strategy);
    }

    @Override
    public double getPrice() {
        if (this.getCustomer().isEligibleForFreeRental()) {
            return 0;
        }

        return super.getPrice();
    }
}
