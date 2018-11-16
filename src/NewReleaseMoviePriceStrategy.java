public class NewReleaseMoviePriceStrategy extends PriceStrategy {
    public NewReleaseMoviePriceStrategy(Rental rental) {
        super(rental);
        this.pricePerDay = 3;
    }
}
