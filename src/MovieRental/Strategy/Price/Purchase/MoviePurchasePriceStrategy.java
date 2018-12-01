package MovieRental.Strategy.Price.Purchase;
import MovieRental.Item.Item;

public class MoviePurchasePriceStrategy extends PurchasePriceStrategy {
    public MoviePurchasePriceStrategy(Item item) {
        super(item);
        this.basePrice = 20;
        this.newReleasePremium = 1.3;

    }
}
