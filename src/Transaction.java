import java.util.List;

interface Transaction {
    List<Item> getRentals();

    void setDaysRented(int daysRented);

    Customer getCustomer();

    void addRental(String title, Movie.Category movieCategory);

    void addRental(String title, Game.Category gameCategory);

    void completeTransaction();

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
