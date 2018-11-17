package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;

public class NewReleaseMovieRentalPriceStrategy extends RentalPriceStrategy {
    public NewReleaseMovieRentalPriceStrategy(Item item) {
        super(item);
        this.pricePerDay = 3;
    }
}
