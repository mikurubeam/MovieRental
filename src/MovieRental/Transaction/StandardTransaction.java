package MovieRental.Transaction;

import MovieRental.Customer.Customer;
import MovieRental.Item.Item;
import MovieRental.Item.Movie;
import MovieRental.Transaction.Statement.Statement;
import MovieRental.Transaction.Statement.TextStatement;
import MovieRental.Transaction.Statement.XmlStatement;

import java.util.*;

public class StandardTransaction implements Transaction {
    private Customer customer;
    private Statement statement;
    private int daysRented;
    private Map<String, Item> rentals;
    private List<Item> purchases;

    public StandardTransaction(Customer customer) {
        this.customer = customer;
        this.rentals = new HashMap<>();
        this.purchases = new ArrayList<>();
        this.statement = new TextStatement(this);
    }

    public List<Movie.Category> getRentedMovieCategories() {
        Map<Movie.Category, Integer> movieTypes = new HashMap<>();
        for (Item item : Item.getFilteredList(this.getRentals(), Movie.class)) {
            movieTypes.put(((Movie) item).getMovieType(), 1);
        }

        return new ArrayList<>(movieTypes.keySet());
    }
    public boolean hasMultipleMovieCategories() {
        return this.hasMultipleMovieCategories(1);
    }

    public boolean hasMultipleMovieCategories(int min) {
        return this.getRentedMovieCategories().size() > min;
    }

    public boolean hasNewReleases() {
        for (Item item : this.getRentals()) {
            if (item.isNewRelease()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Item> getRentals() {
        List<Item> items = new ArrayList<>(this.rentals.values());
        Collections.sort(items);

        return items;
    }

    @Override
    public List<Item> getPurchases() {
        return this.purchases;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return this.daysRented;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void addRental(Item item) {
        item.setTransaction(this);
        this.rentals.put(item.getTitle(), item);
    }

    public void addPurchase(Item item) {
        item.setTransaction(this);
        this.purchases.add(item);
    }

    public void completeTransaction() {
        this.applyDiscounts();
        this.printStatement();
        this.customer.setFrequentRenterPoints(this.getTotalFrequentRenterPoints());
    }

    @Override
    public double getRentalPriceTotal() {
        double total = 0;
        for (Item item : rentals.values()) {
            total += item.getRentalPrice();
        }

        return total;
    }

    @Override
    public double getPurchasePriceSubtotal() {
        double total = 0;
        for (Item item : purchases) {
            total += item.getPurchasePrice();
        }

        return total;
    }

    @Override
    public double getPurchasePriceTotal() {
        return this.getPurchasePriceSubtotal();
    }

    public double getSubtotal()
    {
        return this.getRentalPriceTotal() + this.getPurchasePriceSubtotal();
    }

    public double getTotalPrice() {
        return this.getRentalPriceTotal() + this.getPurchasePriceTotal();
    }

    public int getEarnedFrequentRenterPoints() {
        int points = 0;
        for (Item item : rentals.values()) {
            if (item.getFrequentRentalPoints() > 0) {
                points += item.getFrequentRentalPoints();
            }
        }

        return points;
    }

    public int getSpentFrequentRenterPoints() {
        if (this.customer.isEligibleForFreeRental()) {
            return 10;
        }

        return 0;
    }

    public int getTotalFrequentRenterPoints() {
        return this.customer.getFrequentRenterPoints()
                + this.getEarnedFrequentRenterPoints()
                - this.getSpentFrequentRenterPoints();
    }

    private void applyDiscounts() {
        if (this.purchases.size() > 0) {
            if (this.rentals.size() > 5) {
                this.statement = this.statement.getDiscountStatement(new FiftyPercentDiscountTransaction(this));
            } else if (this.rentals.size() >= 3) {
                this.statement = this.statement.getDiscountStatement(new TwentyPercentDiscountTransaction(this));
            }
        }
    }

    public void printStatementAsText() {
        this.statement = new TextStatement(this);
        applyDiscounts();
        this.printStatement();
    }

    public void printStatementAsXML() {
        this.statement = new XmlStatement(this);
        applyDiscounts();
        this.printStatement();
    }

    public void printStatement() {
        this.statement.generateStatement();
        System.out.println(this.statement);
    }
}
