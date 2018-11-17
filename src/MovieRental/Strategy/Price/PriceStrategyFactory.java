package MovieRental.Strategy.Price;
import MovieRental.Item.Item;
import MovieRental.Item.Movie;
import MovieRental.Item.Game;
import MovieRental.Strategy.Price.Rental.*;

public class PriceStrategyFactory {
    public static PriceStrategy getPriceStrategy(Item item) {
        return getPriceStrategy(item, true);
    }

    public static PriceStrategy getPriceStrategy(Item item, boolean willApplyDiscounts) {
        PriceStrategy strategy = new RentalPriceStrategy(item);

        if (item instanceof Movie) {
            Movie.Category movieType = ((Movie) item).getMovieType();
            if (movieType == Movie.Category.REGULAR) {
                strategy =  new MovieRentalPriceStrategy(item);
            } else if (movieType == Movie.Category.CHILDRENS) {
                strategy =  new ChildrensMovieRentalPriceStrategy(item);
            } else if (movieType == Movie.Category.NEW_RELEASE) {
                strategy =  new NewReleaseMovieRentalPriceStrategy(item);
            }
        } else if (item instanceof Game) {
            strategy =  new GameRentalPriceStrategy(item);
        }

        if (willApplyDiscounts) {
            if (item.isFirstRental()) {
                strategy = new FreeRentalPriceStrategy(strategy);
            }
        }

        return strategy;
    }
}
