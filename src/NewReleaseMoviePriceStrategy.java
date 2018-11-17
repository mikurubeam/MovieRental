public class NewReleaseMoviePriceStrategy extends StandardPriceStrategy {
    public NewReleaseMoviePriceStrategy(Rental rental) {
        super(rental);
        this.pricePerDay = 3;
    }
}
