import java.util.*;

public class StandardTransaction implements Transaction {
    private Customer customer;
    private Statement statement;
    private int daysRented;
    private Map<String, Rental> rentals;

    public StandardTransaction(Customer customer) {
        this.customer = customer;
        this.rentals = new HashMap<>();
        this.statement = new TextStatement(this);
    }

    public List<Movie.Category> getRentedMovieCategories() {
        Map<Movie.Category, Integer> movieTypes = new HashMap<>();
        for (Rental rental : Rental.getFilteredList(this.getRentals(), Movie.class)) {
            movieTypes.put(((Movie)rental).getMovieType(), 1);
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
        for (Rental rental : Rental.getFilteredList(this.getRentals(), Movie.class)) {
            if (((Movie)rental).getMovieType() == Movie.Category.NEW_RELEASE) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Rental> getRentals() {
        List<Rental> rentals = new ArrayList<>(this.rentals.values());
        Collections.sort(rentals);

        return rentals;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void addRental(String title, Movie.Category movieCategory) {
        this.rentals.put(title, RentalFactory.getRental(title, this.daysRented, movieCategory, this));
    }

    public void addRental(String title, Game.Category gameCategory) {
        this.rentals.put(title, RentalFactory.getRental(title, this.daysRented, gameCategory, this));
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
        for (Rental rental : rentals.values()) {
            total += rental.getRentalPrice();
        }

        return total;
    }

    public int getEarnedFrequentRenterPoints() {
        int points = 0;
        for (Rental rental : rentals.values()) {
            if (rental.getFrequentRentalPoints() > 0) {
                points += rental.getFrequentRentalPoints();
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
