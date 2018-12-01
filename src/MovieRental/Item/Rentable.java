package MovieRental.Item;

public interface Rentable {
    int getFrequentRentalPoints();

    double getRentalPrice();

    double getRentalPrice(boolean willApplyDiscounts);

    boolean isFirstRental();

    int getDaysRented();
}
