package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;

public class AlbumCompactDiscRentalPriceStrategy extends RentalPriceStrategy {
    public AlbumCompactDiscRentalPriceStrategy(Item item) {
        super(item);
        this.freeRentalDays = 5;
        this.basePrice = 2;
        this.pricePerDay = 0.75;
    }
}
