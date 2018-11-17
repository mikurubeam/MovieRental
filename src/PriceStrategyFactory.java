public class PriceStrategyFactory {
    public static PriceStrategy getPriceStrategy(Item item) {
        return getPriceStrategy(item, true);
    }

    public static PriceStrategy getPriceStrategy(Item item, boolean willApplyDiscounts) {
        PriceStrategy strategy = new StandardPriceStrategy(item);

        if (item instanceof Movie) {
            Movie.Category movieType = ((Movie) item).getMovieType();
            if (movieType == Movie.Category.REGULAR) {
                strategy =  new MoviePriceStrategy(item);
            } else if (movieType == Movie.Category.CHILDRENS) {
                strategy =  new ChildrensMoviePriceStrategy(item);
            } else if (movieType == Movie.Category.NEW_RELEASE) {
                strategy =  new NewReleaseMoviePriceStrategy(item);
            }
        } else if (item instanceof Game) {
            strategy =  new GamePriceStrategy(item);
        }

        if (willApplyDiscounts) {
            if (item.isFirstRental()) {
                strategy = new FreeRentalPriceStrategy(strategy);
            }
        }

        return strategy;
    }
}
