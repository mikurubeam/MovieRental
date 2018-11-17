public class FrequentRenterPointStrategyDecorator implements FrequentRenterPointStrategy {
    protected FrequentRenterPointStrategy strategy;
    protected Customer customer;

    public FrequentRenterPointStrategyDecorator(FrequentRenterPointStrategy strategy)
    {
        this.strategy = strategy;
        this.customer = strategy.getRental().getCustomer();
    }

    @Override
    public int getFrequentRentalPoints() {
        return this.strategy.getFrequentRentalPoints();
    }

    @Override
    public Rental getRental() {
        return this.strategy.getRental();
    }
}
