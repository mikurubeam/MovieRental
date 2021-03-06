public class MultipleCategoryFrequentRenterPointStrategy extends FrequentRenterPointStrategyDecorator{
    public MultipleCategoryFrequentRenterPointStrategy(FrequentRenterPointStrategy strategy) {
        super(strategy);
    }

    public int getFrequentRentalPoints() {
        int points = super.getFrequentRentalPoints();

        if (this.getTransaction().hasMultipleMovieCategories(2)) {
            points *= 2;
        }

        return points;
    }
}
