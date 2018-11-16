public class NewReleaseFrequentRenterPointStrategy extends FrequentRenterPointStrategyDecorator {
    public NewReleaseFrequentRenterPointStrategy(FrequentRenterPointStrategy strategy) {
        super(strategy);
    }

    @Override
    public int getFrequentRentalPoints() {
        int points = super.getFrequentRentalPoints();

        if (this.getRental().getDaysRented() > 1) {
            points += 1;
        }

        return points;
    }
}
