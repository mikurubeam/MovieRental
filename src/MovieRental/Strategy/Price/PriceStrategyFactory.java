package MovieRental.Strategy.Price;
import MovieRental.Item.*;
import MovieRental.Strategy.Price.Purchase.*;
import MovieRental.Strategy.Price.Rental.*;

public class PriceStrategyFactory {
    public static PriceStrategy getRentalPriceStrategy(Item item) {
        return getRentalPriceStrategy(item, true);
    }

    public static PriceStrategy getRentalPriceStrategy(Item item, boolean willApplyDiscounts) {
        PriceStrategy strategy = new RentalPriceStrategy(item);

        if (item instanceof Movie) {
            Movie.Category movieType = ((Movie) item).getMovieType();

            if (item.isNewRelease()) {
                strategy = new NewReleaseMovieRentalPriceStrategy(item);
            } else if (movieType == Movie.Category.REGULAR) {
                strategy =  new MovieRentalPriceStrategy(item);
            } else if (movieType == Movie.Category.CHILDRENS) {
                strategy =  new ChildrensMovieRentalPriceStrategy(item);
            }

            if (item instanceof Dvd) {
                strategy = new DvdRentalPriceStrategy(strategy);
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

    public static PriceStrategy getPurchasePriceStrategy(Item item) {
        PriceStrategy strategy = new PurchasePriceStrategy(item);

        if (item instanceof Movie) {
            Movie.Category movieType = ((Movie) item).getMovieType();

            if (movieType == Movie.Category.REGULAR) {
                strategy =  new MoviePurchasePriceStrategy(item);
            } else if (movieType == Movie.Category.CHILDRENS) {
                strategy =  new ChildrensMoviePurchasePriceStrategy(item);
            }

            if (item.isNewRelease()) {
                strategy = new NewReleasePurchasePriceStrategy(strategy);
            }

            if (item instanceof Dvd) {
                strategy = new DvdPurchasePriceStrategy(strategy);
            }

        } else if (item instanceof Game) {
            strategy =  new GamePurchasePriceStrategy(item);
        }

        return strategy;
    }
}