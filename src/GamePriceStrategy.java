public class GamePriceStrategy extends StandardPriceStrategy {
    public GamePriceStrategy(Item item) {
        super(item);
    }

    @Override
    public double getPrice() {
        return 5.0;
    }
}
