package MovieRental.Strategy.FrequentRenterPoint;
import MovieRental.Item.Item;
import MovieRental.Customer.Customer;
import MovieRental.Transaction.Transaction;

public interface FrequentRenterPointStrategy {
    int getFrequentRentalPoints();
    Item getItem();
    Customer getCustomer();
    Transaction getTransaction();
}
