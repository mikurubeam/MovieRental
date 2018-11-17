public class FrequentRenterPointStrategyDecorator implements FrequentRenterPointStrategy {
    protected FrequentRenterPointStrategy strategy;

    public FrequentRenterPointStrategyDecorator(FrequentRenterPointStrategy strategy)
    {
        this.strategy = strategy;
    }

    @Override
    public int getFrequentRentalPoints() {
        return this.strategy.getFrequentRentalPoints();
    }

    @Override
    public Rental getRental() {
        return this.strategy.getRental();
    }

    @Override
    public Customer getCustomer() {
        return this.strategy.getCustomer();
    }

    @Override
    public Transaction getTransaction() {
        return this.strategy.getTransaction();
    }
}
