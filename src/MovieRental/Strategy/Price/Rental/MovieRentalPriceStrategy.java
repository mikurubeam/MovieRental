package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;

public class MovieRentalPriceStrategy extends RentalPriceStrategy {
    public MovieRentalPriceStrategy(Item item) {
        super(item);
        this.freeRentalDays = 2;
        this.basePrice = 2;
        this.pricePerDay = 1.5;
    }
}
