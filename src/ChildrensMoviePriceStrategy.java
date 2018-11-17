public class ChildrensMoviePriceStrategy extends StandardPriceStrategy {
    public ChildrensMoviePriceStrategy(Rental rental) {
        super(rental);
        this.freeRentalDays = 3;
        this.basePrice = 1.5;
        this.pricePerDay = 1.5;
    }
}
