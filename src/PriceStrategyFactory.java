public class PriceStrategyFactory {
    public static PriceStrategy getPriceStrategy(Rental rental) {
        if (rental instanceof Movie) {
            Movie.Category movieType = ((Movie)rental).getMovieType();
            if (movieType == Movie.Category.REGULAR) {
                return new MoviePriceStrategy(rental);
            } else if (movieType == Movie.Category.CHILDRENS) {
                return new ChildrensMoviePriceStrategy(rental);
            } else if (movieType == Movie.Category.NEW_RELEASE) {
                return new NewReleaseMoviePriceStrategy(rental);
            }
        } else if (rental instanceof Game) {
            return new GamePriceStrategy(rental);
        }

        return new PriceStrategy(rental);
    }
}
