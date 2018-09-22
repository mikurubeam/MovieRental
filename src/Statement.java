import java.util.List;

public class Statement {
    private double totalAmount;
    private int frequentRenterPoints;
    private StringBuilder statementBuilder;

    public Statement(Customer customer) {
        this.totalAmount = 0;
        this.statementBuilder = new StringBuilder();

        // Initialize Customer specific values
        this.frequentRenterPoints = customer.getFrequentRenterPoints();
        this.statementBuilder.append(String.format("Rental Record for %s:\n", customer.getName()));
    }

    public void addMovieSummary(List<Movie> movies) {
        this.statementBuilder.append(Movie.getStatementHeader());
        for (Movie movie : movies) {
            this.totalAmount += movie.getRentalPrice();
            this.frequentRenterPoints += movie.getBonusPoints();
            this.statementBuilder.append(movie);
        }
        this.statementBuilder.append("\n");
    }

    public void addStatementFooter() {
        this.statementBuilder.append("\t");
        this.statementBuilder.append(StringUtil.padLeft("Amount owed is:", 45));
        this.statementBuilder.append(
                StringUtil.padLeft(String.format(StringUtil.USD, this.totalAmount), StringUtil.LEFT_PAD)
        );
        this.statementBuilder.append(
                String.format("\n\nYou earned %d frequent renter points!\n", frequentRenterPoints)
        );
        this.statementBuilder.append(String.format("Total frequent renter points: %d\n", this.frequentRenterPoints));
    }

    @Override
    public String toString() {
        return this.statementBuilder.toString();
    }
}
