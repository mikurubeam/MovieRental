package MovieRental.Strategy.Price.Rental;

import MovieRental.Strategy.Price.PriceStrategy;
import MovieRental.Strategy.Price.PriceStrategyDecorator;

public class DvdRentalPriceStrategy extends PriceStrategyDecorator {
    public DvdRentalPriceStrategy(PriceStrategy strategy) {
        super(strategy);
    }

    @Override
    public double getPrice() {
        RentalPriceStrategy strategy = (RentalPriceStrategy) this.strategy;
        double basePrice = strategy.getBasePrice() + 1;
        double pricePerDay = strategy.getPricePerDay() + 0.5;
        int paidRentalDays = strategy.getPaidRentalDays();

        return basePrice + (pricePerDay * paidRentalDays);
    }
}
