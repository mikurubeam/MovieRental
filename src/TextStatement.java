import java.util.List;

public class TextStatement extends Statement {
    private static final int BASE_CONTENT_LENGTH = 45;

    private final StringBuilder statementBuilder;

    public TextStatement(Transaction transaction) {
        super(transaction);
        this.statementBuilder = new StringBuilder();
    }

    @Override
    public void addStatementHeader() {
        this.statementBuilder.append(String.format("Rental Record for %s:\n", this.customer.getName()));
    }

    @Override
    public void addRentalSummaryByType(List<Rental> rentals) {
        if (rentals.isEmpty()) {
            return;
        }

        // Summary header by type
        this.statementBuilder.append(rentals.get(0).getTableHeader());

        // Itemized list of rentals by type
        for (Rental rental : rentals) {
            this.statementBuilder.append(rental);
        }

        this.statementBuilder.append("\n");
    }

    @Override
    public void addStatementFooter() {
        // Add footer data to string builder
        this.statementBuilder.append("\t");
        this.statementBuilder.append(StringUtil.padLeft("Subtotal:", BASE_CONTENT_LENGTH));
        this.statementBuilder.append(
                StringUtil.padLeft(String.format(StringUtil.USD, this.getSubtotal()), StringUtil.LEFT_PAD)
        );
        this.statementBuilder.append("\n");
        this.statementBuilder.append(
                StringUtil.padLeft(String.format("%d Items Rented", this.getRentals().size()), BASE_CONTENT_LENGTH)
        );
        if (this.transaction instanceof PercentDiscountTransaction) {
                this.statementBuilder.append(String.format(" (%s Discount!)", this.transaction));
        }
        this.statementBuilder.append("\n");
        this.statementBuilder.append(StringUtil.padLeft("Total:", BASE_CONTENT_LENGTH));
        this.statementBuilder.append(
                StringUtil.padLeft(String.format(StringUtil.USD, this.getTotalPrice()), StringUtil.LEFT_PAD)
        );
        this.statementBuilder.append(
                String.format("\n\nCurrent frequent renter points:  %d\n", this.customer.getFrequentRenterPoints())
        );
        this.statementBuilder.append(
                String.format("You earned %d frequent renter points!\n", this.getEarnedFrequentRenterPoints())
        );
        if (this.getSpentFrequentRenterPoints() > 0) {
            this.statementBuilder.append(
                    String.format("You redeemed %d frequent renter points!\n", this.getSpentFrequentRenterPoints())
            );
        }
        this.statementBuilder.append(
                String.format("Total frequent renter points: %d\n", this.getTotalFrequentRenterPoints())
        );
    }

    @Override
    public String toString() {
        return this.statementBuilder.toString();
    }
}
