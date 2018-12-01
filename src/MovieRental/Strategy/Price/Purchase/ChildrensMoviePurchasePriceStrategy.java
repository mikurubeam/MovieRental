package MovieRental.Strategy.Price.Purchase;
import MovieRental.Item.Item;
import MovieRental.Strategy.Price.Rental.RentalPriceStrategy;

public class ChildrensMoviePurchasePriceStrategy extends PurchasePriceStrategy {
    public ChildrensMoviePurchasePriceStrategy(Item item) {
        super(item);
        this.basePrice = 15;
        this.newReleasePremium = 1.2;

    }
}
