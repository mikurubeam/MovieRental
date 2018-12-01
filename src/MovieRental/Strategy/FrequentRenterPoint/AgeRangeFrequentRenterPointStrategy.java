package MovieRental.Strategy.FrequentRenterPoint;

public class AgeRangeFrequentRenterPointStrategy extends FrequentRenterPointStrategyDecorator {
    public AgeRangeFrequentRenterPointStrategy(FrequentRenterPointStrategy strategy) {
        super(strategy);
    }

    public int getFrequentRentalPoints() {
        int points = super.getFrequentRentalPoints();

        if (this.getCustomer() != null
                && this.getCustomer().isInAgeRange(18, 22) && this.getTransaction().hasNewReleases()
        ) {
            points *= 2;
        }

        return points;
    }
}
