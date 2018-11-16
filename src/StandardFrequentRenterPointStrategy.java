public class StandardFrequentRenterPointStrategy implements FrequentRenterPointStrategy{
    protected int bonusPoints;
    protected Rental rental;

    public StandardFrequentRenterPointStrategy(Rental rental) {
        this.bonusPoints = 1;
        this.rental = rental;
    }

    public int getFrequentRentalPoints() {
        return this.bonusPoints;
    }

    public Rental getRental() {
        return this.rental;
    }
}
