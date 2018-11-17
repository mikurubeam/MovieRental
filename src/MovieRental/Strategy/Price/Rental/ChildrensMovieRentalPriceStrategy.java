package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;

public class ChildrensMovieRentalPriceStrategy extends RentalPriceStrategy {
    public ChildrensMovieRentalPriceStrategy(Item item) {
        super(item);
        this.freeRentalDays = 3;
        this.basePrice = 1.5;
        this.pricePerDay = 1.5;
    }
}
