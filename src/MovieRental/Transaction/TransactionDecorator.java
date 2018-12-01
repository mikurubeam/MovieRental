package MovieRental.Transaction;

import MovieRental.Customer.Customer;
import MovieRental.Item.Game;
import MovieRental.Item.Item;
import MovieRental.Item.Movie;

import java.util.List;

public class TransactionDecorator implements Transaction {
    public final Transaction transaction;

    public TransactionDecorator(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List<Item> getRentals() {
        return this.transaction.getRentals();
    }

    @Override
    public void setDaysRented(int daysRented) {
        this.transaction.setDaysRented(daysRented);
    }

    @Override
    public Customer getCustomer() {
        return this.transaction.getCustomer();
    }

    @Override
    public void addRental(Item item) {
        this.transaction.addRental(item);
    }

    @Override
    public void completeTransaction() {
        this.transaction.completeTransaction();
    }

    @Override
    public double getSubtotal() {
        return this.transaction.getSubtotal();
    }

    @Override
    public double getTotalPrice() {
        return this.transaction.getTotalPrice();
    }

    @Override
    public int getEarnedFrequentRenterPoints() {
        return this.transaction.getEarnedFrequentRenterPoints();
    }

    @Override
    public int getSpentFrequentRenterPoints() {
        return this.transaction.getSpentFrequentRenterPoints();
    }

    @Override
    public int getTotalFrequentRenterPoints() {
        return this.transaction.getTotalFrequentRenterPoints();
    }

    @Override
    public void printStatement() {
        this.transaction.printStatement();
    }

    @Override
    public List<Movie.Category> getRentedMovieCategories() {
        return this.transaction.getRentedMovieCategories();
    }

    @Override
    public boolean hasMultipleMovieCategories(int min) {
        return this.transaction.hasMultipleMovieCategories(min);
    }

    @Override
    public boolean hasNewReleases() {
        return this.transaction.hasNewReleases();
    }

    @Override
    public int getDaysRented() {
        return this.transaction.getDaysRented();
    }

    @Override
    public List<Item> getPurchases() {
        return this.transaction.getPurchases();
    }

    @Override
    public void addPurchase(Item item) {
        this.transaction.addPurchase(item);
    }

    @Override
    public double getRentalPriceTotal() {
        return this.transaction.getRentalPriceTotal();
    }

    @Override
    public double getPurchasePriceTotal() {
        return this.transaction.getPurchasePriceTotal();
    }

    @Override
    public double getPurchasePriceSubtotal() {
        return this.transaction.getPurchasePriceSubtotal();
    }
}
