public class FreeRentalFrequentRenterPointStrategy extends FrequentRenterPointStrategyDecorator {
    public FreeRentalFrequentRenterPointStrategy(FrequentRenterPointStrategy strategy) {
        super(strategy);
    }

    public int getFrequentRentalPoints()
    {
        if (this.getCustomer().isEligibleForFreeRental()) {
            return -10;
        }

        return super.getFrequentRentalPoints();
    }
}
