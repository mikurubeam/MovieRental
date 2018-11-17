public interface FrequentRenterPointStrategy {
    int getFrequentRentalPoints();
    Rental getRental();
    Customer getCustomer();
    Transaction getTransaction();
}
