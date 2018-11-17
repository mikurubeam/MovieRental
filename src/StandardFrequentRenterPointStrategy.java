public class StandardFrequentRenterPointStrategy implements FrequentRenterPointStrategy{
    protected int bonusPoints;
    protected Rental rental;
    protected Customer customer;
    protected Transaction transaction;

    public StandardFrequentRenterPointStrategy(Rental rental) {
        this.bonusPoints = 1;
        this.rental = rental;
        this.customer = rental.getCustomer();
        this.transaction = rental.getTransaction();
    }

    public int getFrequentRentalPoints() {
        return this.bonusPoints;
    }

    public Rental getRental() {
        return this.rental;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }
}
