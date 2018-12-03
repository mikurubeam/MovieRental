package MovieRental.Strategy.FrequentRenterPoint;

public class MultipleCategoryFrequentRenterPointStrategy extends FrequentRenterPointStrategyDecorator {
    public MultipleCategoryFrequentRenterPointStrategy(FrequentRenterPointStrategy strategy) {
        super(strategy);
    }

    public int getFrequentRentalPoints() {
        int points = super.getFrequentRentalPoints();

        if (this.getTransaction() != null && this.getTransaction().hasMultipleMovieCategories(1)) {
            points *= 2;
        }

        return points;
    }
}
