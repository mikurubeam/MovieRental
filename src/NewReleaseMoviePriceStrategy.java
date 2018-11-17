public class NewReleaseMoviePriceStrategy extends StandardPriceStrategy {
    public NewReleaseMoviePriceStrategy(Item item) {
        super(item);
        this.pricePerDay = 3;
    }
}
