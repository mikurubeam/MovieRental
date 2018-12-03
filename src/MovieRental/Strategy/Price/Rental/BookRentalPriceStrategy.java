package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;

public class BookRentalPriceStrategy extends RentalPriceStrategy {
    public BookRentalPriceStrategy(Item item) {
        super(item);
        this.freeRentalDays = 5;
        this.basePrice = 3;
        this.pricePerDay = 0.5;
    }
}
