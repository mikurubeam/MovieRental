package MovieRental.Strategy.Price.Purchase;
import MovieRental.Item.Item;

public class GamePurchasePriceStrategy extends PurchasePriceStrategy {
    public GamePurchasePriceStrategy(Item item) {
        super(item);
        this.basePrice = 30;
        this.newReleasePremium = 1.5;
    }
}
