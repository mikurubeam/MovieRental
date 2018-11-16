public class GamePriceStrategy extends PriceStrategy {
    public GamePriceStrategy(Rental rental) {
        super(rental);
    }

    @Override
    public double getPrice() {
        return 0.0;
    }
}
