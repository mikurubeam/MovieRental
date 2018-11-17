public class PriceStrategyDecorator implements PriceStrategy {
    private PriceStrategy strategy;

    public PriceStrategyDecorator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double getPrice() {
        return this.strategy.getPrice();
    }

    @Override
    public Rental getRental() {
        return this.strategy.getRental();
    }

    @Override
    public Customer getCustomer() {
        return this.strategy.getCustomer();
    }
}
