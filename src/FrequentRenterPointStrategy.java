public interface FrequentRenterPointStrategy {
    int getFrequentRentalPoints();
    Item getItem();
    Customer getCustomer();
    Transaction getTransaction();
}
