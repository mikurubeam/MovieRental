public class PriceStrategyFactory {
    public static PriceStrategy getPriceStrategy(Rental rental) {
        return getPriceStrategy(rental, true);
    }

    public static PriceStrategy getPriceStrategy(Rental rental, boolean willApplyDiscounts) {
        PriceStrategy strategy = new StandardPriceStrategy(rental);

        if (rental instanceof Movie) {
            Movie.Category movieType = ((Movie)rental).getMovieType();
            if (movieType == Movie.Category.REGULAR) {
                strategy =  new MoviePriceStrategy(rental);
            } else if (movieType == Movie.Category.CHILDRENS) {
                strategy =  new ChildrensMoviePriceStrategy(rental);
            } else if (movieType == Movie.Category.NEW_RELEASE) {
                strategy =  new NewReleaseMoviePriceStrategy(rental);
            }
        } else if (rental instanceof Game) {
            strategy =  new GamePriceStrategy(rental);
        }

        if (willApplyDiscounts) {
            if (rental.isFirstRental()) {
                strategy = new FreeRentalPriceStrategy(strategy);
            }
        }

        return strategy;
    }
}
