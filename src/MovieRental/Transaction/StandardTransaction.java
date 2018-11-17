package MovieRental.Transaction;

import MovieRental.Customer.Customer;
import MovieRental.Item.Game;
import MovieRental.Item.Item;
import MovieRental.Item.ItemFactory;
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

    public StandardTransaction(Customer customer) {
        this.customer = customer;
        this.rentals = new HashMap<>();
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
        for (Item item : Item.getFilteredList(this.getRentals(), Movie.class)) {
            if (((Movie) item).getMovieType() == Movie.Category.NEW_RELEASE) {
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

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void addRental(String title, Movie.Category movieCategory) {
        this.rentals.put(title, ItemFactory.getItem(title, this.daysRented, movieCategory, this));
    }

    public void addRental(String title, Game.Category gameCategory) {
        this.rentals.put(title, ItemFactory.getItem(title, this.daysRented, gameCategory, this));
    }

    public void completeTransaction() {
        this.applyDiscounts();
        this.printStatement();
        this.customer.setFrequentRenterPoints(this.getTotalFrequentRenterPoints());
    }

    public double getSubtotal() {
        return this.getTotalPrice();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Item item : rentals.values()) {
            total += item.getRentalPrice();
        }

        return total;
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
        if (this.rentals.size() > 5) {
            this.statement = this.statement.getDiscountStatement(new FiftyPercentDiscountTransaction(this));
        } else if (this.rentals.size() >= 3) {
            this.statement = this.statement.getDiscountStatement(new TwentyPercentDiscountTransaction(this));
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
