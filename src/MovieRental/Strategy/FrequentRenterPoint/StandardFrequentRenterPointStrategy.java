package MovieRental.Strategy.FrequentRenterPoint;
import MovieRental.Item.Item;
import MovieRental.Customer.Customer;
import MovieRental.Transaction.Transaction;

public class StandardFrequentRenterPointStrategy implements FrequentRenterPointStrategy {
    protected int bonusPoints;
    protected Item item;
    protected Customer customer;
    protected Transaction transaction;

    public StandardFrequentRenterPointStrategy(Item item) {
        this.bonusPoints = 1;
        this.item = item;
        this.customer = item.getCustomer();
        this.transaction = item.getTransaction();
    }

    public int getFrequentRentalPoints() {
        return this.bonusPoints;
    }

    public Item getItem() {
        return this.item;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }
}
