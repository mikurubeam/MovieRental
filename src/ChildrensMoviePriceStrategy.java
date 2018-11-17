public class ChildrensMoviePriceStrategy extends StandardPriceStrategy {
    public ChildrensMoviePriceStrategy(Item item) {
        super(item);
        this.freeRentalDays = 3;
        this.basePrice = 1.5;
        this.pricePerDay = 1.5;
    }
}
