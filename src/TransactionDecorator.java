import java.util.List;

public class TransactionDecorator implements Transaction {
    final Transaction transaction;

    public TransactionDecorator(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List<Rental> getRentals() {
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
    public void addRental(String title, Movie.Category movieCategory) {
        this.transaction.addRental(title, movieCategory);
    }

    @Override
    public void addRental(String title, Game.Category gameCategory) {
        this.transaction.addRental(title, gameCategory);
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
}
