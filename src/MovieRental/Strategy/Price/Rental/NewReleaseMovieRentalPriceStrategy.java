package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;
import MovieRental.Strategy.Price.PriceStrategy;
import MovieRental.Strategy.Price.PriceStrategyDecorator;

public class NewReleaseMovieRentalPriceStrategy extends PriceStrategyDecorator {
    public NewReleaseMovieRentalPriceStrategy(PriceStrategy strategy) {
        super(strategy);
    }

    public double getPrice() {
        return super.getPrice() * 1.5;
    }
}
