public class GamePriceStrategy extends StandardPriceStrategy {
    public GamePriceStrategy(Rental rental) {
        super(rental);
    }

    @Override
    public double getPrice() {
        return 5.0;
    }
}
