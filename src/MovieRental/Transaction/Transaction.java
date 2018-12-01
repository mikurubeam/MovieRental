package MovieRental.Transaction;

import MovieRental.Customer.Customer;
import MovieRental.Item.Game;
import MovieRental.Item.Item;
import MovieRental.Item.Movie;

import java.util.List;

public interface Transaction {
    List<Item> getRentals();
    List<Item> getPurchases();

    void setDaysRented(int daysRented);

    int getDaysRented();

    Customer getCustomer();

    void addRental(Item item);

    void addPurchase(Item item);

    void completeTransaction();

    double getRentalPriceTotal();

    double getPurchasePriceSubtotal();

    double getPurchasePriceTotal();

    double getSubtotal();

    double getTotalPrice();

    int getEarnedFrequentRenterPoints();

    int getSpentFrequentRenterPoints();

    int getTotalFrequentRenterPoints();

    void printStatement();

    List<Movie.Category> getRentedMovieCategories();

    boolean hasMultipleMovieCategories(int min);

    boolean hasNewReleases();
}
