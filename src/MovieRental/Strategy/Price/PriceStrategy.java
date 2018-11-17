package MovieRental.Strategy.Price;
import MovieRental.Item.Item;
import MovieRental.Customer.Customer;

public interface PriceStrategy {
    double getPrice();
    Item getItem();
    Customer getCustomer();
}
