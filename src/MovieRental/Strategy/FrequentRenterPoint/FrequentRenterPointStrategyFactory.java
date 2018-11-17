package MovieRental.Strategy.FrequentRenterPoint;
import MovieRental.Item.Item;
import MovieRental.Item.Movie;

public class FrequentRenterPointStrategyFactory {
    public static FrequentRenterPointStrategy getFrequentRentalPointStrategy(Item item) {
        FrequentRenterPointStrategy strategy = new StandardFrequentRenterPointStrategy(item);

        if (item instanceof Movie) {
            strategy = new MultipleCategoryFrequentRenterPointStrategy(strategy);
            strategy = new AgeRangeFrequentRenterPointStrategy(strategy);

            if (((Movie) item).getMovieType() == Movie.Category.NEW_RELEASE) {
                strategy =  new NewReleaseFrequentRenterPointStrategy(strategy);
            }
        }

        if (item.isFirstRental()) {
            strategy = new FreeRentalFrequentRenterPointStrategy(strategy);
        }

        return strategy;
    }
}
