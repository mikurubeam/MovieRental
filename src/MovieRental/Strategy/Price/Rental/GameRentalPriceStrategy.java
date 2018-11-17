package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;

public class GameRentalPriceStrategy extends RentalPriceStrategy {
    public GameRentalPriceStrategy(Item item) {
        super(item);
    }

    @Override
    public double getPrice() {
        return 5.0;
    }
}
