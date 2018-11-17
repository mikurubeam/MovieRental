public class FrequentRenterPointStrategyFactory {
    public static FrequentRenterPointStrategy getFrequentRentalPointStrategy(Rental rental) {
        FrequentRenterPointStrategy strategy = new StandardFrequentRenterPointStrategy(rental);

        if (rental instanceof Movie) {
            strategy = new MultipleCategoryFrequentRenterPointStrategy(strategy);
            strategy = new AgeRangeFrequentRenterPointStrategy(strategy);

            if (((Movie)rental).getMovieType() == Movie.Category.NEW_RELEASE) {
                strategy =  new NewReleaseFrequentRenterPointStrategy(strategy);
            }
        }

        if (rental.isFirstRental()) {
            strategy = new FreeRentalFrequentRenterPointStrategy(strategy);
        }

        return strategy;
    }
}
