public class FreeRentalPriceStrategy extends PriceStrategyDecorator {
    public FreeRentalPriceStrategy(PriceStrategy strategy) {
        super(strategy);
    }

    @Override
    public double getPrice() {
        if (this.getCustomer().isEligibleForFreeRental()) {
            return 0;
        }

        return super.getPrice();
    }
}
