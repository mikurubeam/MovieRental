public class MoviePriceStrategy extends PriceStrategy {
    public MoviePriceStrategy(Rental rental) {
        super(rental);
        this.freeRentalDays = 2;
        this.basePrice = 2;
        this.pricePerDay = 1.5;
    }
}
