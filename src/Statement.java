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

    public void addRentalSummary(List<Rental> rentals) {
        this.addSummaryByType(rentals, Movie.class);
        this.addSummaryByType(rentals, Game.class);
    }

    private <T> void addSummaryByType(List<Rental> rentals, Class<T> objectType) {
        List<Rental> filteredList = Rental.getFilteredList(rentals, objectType);

        if (filteredList.isEmpty()) {
            return;
        }

        // Summary header by type
        this.statementBuilder.append(filteredList.get(0).getTableHeader());

        // Itemized list of rentals by type
        for (Rental rental : filteredList) {
            this.totalAmount += rental.getRentalPrice();
            this.frequentRenterPoints += rental.getBonusPoints();
            this.statementBuilder.append(rental);
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
