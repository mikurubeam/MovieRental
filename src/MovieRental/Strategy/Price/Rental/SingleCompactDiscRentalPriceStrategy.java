package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;

public class SingleCompactDiscRentalPriceStrategy extends RentalPriceStrategy {
    public SingleCompactDiscRentalPriceStrategy(Item item) {
        super(item);
        this.freeRentalDays = 3;
        this.basePrice = 1;
        this.pricePerDay = 0.5;
    }
}
