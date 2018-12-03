package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;

public class GameRentalPriceStrategy extends RentalPriceStrategy {
    public GameRentalPriceStrategy(Item item) {
        super(item);
        this.freeRentalDays = 3;
        this.basePrice = 4;
        this.pricePerDay = 1;
    }
}
