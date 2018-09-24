import java.util.List;

public class TextStatement extends Statement {
    private static final int BASE_CONTENT_LENGTH = 45;

    private StringBuilder statementBuilder;

    public TextStatement(Customer customer) {
        super(customer);
        this.statementBuilder = new StringBuilder();
    }

    protected void addStatementHeaderData() {
        this.statementBuilder.append(String.format("Rental Record for %s:\n", this.customer.getName()));
    }

    protected void addRentalSummaryByType(List<Rental> rentals) {
        if (rentals.isEmpty()) {
            return;
        }

        // Summary header by type
        this.statementBuilder.append(rentals.get(0).getTableHeader());

        // Itemized list of rentals by type
        for (Rental rental : rentals) {
            this.totalAmount += rental.getRentalPrice();
            this.frequentRenterPoints += rental.getBonusPoints();
            this.statementBuilder.append(rental);
        }

        this.statementBuilder.append("\n");
    }

    public void addStatementFooterData() {
        // Add footer data to string builder
        this.statementBuilder.append("\t");
        this.statementBuilder.append(StringUtil.padLeft("Amount owed is:", BASE_CONTENT_LENGTH));
        this.statementBuilder.append(
                StringUtil.padLeft(String.format(StringUtil.USD, this.totalAmount), StringUtil.LEFT_PAD)
        );
        this.statementBuilder.append(
                String.format("\n\nYou earned %d frequent renter points!\n", this.frequentRenterPoints)
        );
        this.statementBuilder.append(
                String.format("Total frequent renter points: %d\n", this.getTotalFrequentRenterPoints())
        );
    }

    @Override
    public String toString() {
        return this.statementBuilder.toString();
    }
}
