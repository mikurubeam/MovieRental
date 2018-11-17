public class MoviePriceStrategy extends StandardPriceStrategy {
    public MoviePriceStrategy(Item item) {
        super(item);
        this.freeRentalDays = 2;
        this.basePrice = 2;
        this.pricePerDay = 1.5;
    }
}
