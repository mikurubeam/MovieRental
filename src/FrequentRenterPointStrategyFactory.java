public class FrequentRenterPointStrategyFactory {
    public static FrequentRenterPointStrategy getFrequentRentalPointStrategy(Rental rental) {
        FrequentRenterPointStrategy strategy;

        if (rental instanceof Movie) {
            Movie movie = (Movie)rental;

            if (movie.isFirstRental()) {
                strategy = new FirstRentalFrequentRenterPointStrategy(movie);
            } else {
                strategy = new StandardFrequentRenterPointStrategy(movie);
            }

            if (movie.getMovieType() == Movie.Category.NEW_RELEASE) {
                return new NewReleaseFrequentRenterPointStrategy(strategy);
            }

            return strategy;
        }

        return new StandardFrequentRenterPointStrategy(rental);
    }
}
