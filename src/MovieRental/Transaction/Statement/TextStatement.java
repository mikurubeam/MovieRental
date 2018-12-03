package MovieRental.Transaction.Statement;

import MovieRental.Item.Item;
import MovieRental.Transaction.PercentDiscountTransaction;
import MovieRental.Transaction.Transaction;
import MovieRental.Util.StringUtil;

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
        this.statementBuilder.append(String.format("Transaction Record for %s:\n", this.customer.getName()));
    }

    @Override
    public void addRentalSummaryByType(List<Item> items) {
        if (items.isEmpty()) {
            return;
        }

        // Itemized list of items by type
        for (Item item : items) {
            // Summary header by type
            if (items.indexOf(item) == 0) {
                this.statementBuilder.append(item.getRentalHeader());
            }

            this.statementBuilder.append(item.getRentalString());
        }

        this.statementBuilder.append("\n");
    }

    @Override
    public void addPurchaseHeader() {
        this.statementBuilder.append("Purchases:\n");
    }

    @Override
    public void addRentalHeader() {
        this.statementBuilder.append("Rentals:\n");
    }

    @Override
    public void addPurchaseSummaryByType(List<Item> items) {
        if (items.isEmpty()) {
            return;
        }

        // Itemized list of items by type
        for (Item item : items) {
            // Summary header by type
            if (items.indexOf(item) == 0) {
                this.statementBuilder.append(item.getPurchaseHeader());
            }

            this.statementBuilder.append(item.getPurchaseString());
        }

        this.statementBuilder.append("\n");
    }

    @Override
    public void addStatementFooter() {
        // Add footer data to string builder
        this.statementBuilder.append("\t");
        this.statementBuilder.append(StringUtil.padLeft("Rental Subtotal:", BASE_CONTENT_LENGTH));
        this.statementBuilder.append(
                StringUtil.padLeft(String.format(StringUtil.USD, this.getRentalPriceTotal()), StringUtil.LEFT_PAD)
        );
        this.statementBuilder.append("\n");
        this.statementBuilder.append("\t");
        this.statementBuilder.append(StringUtil.padLeft("Purchase Subtotal:", BASE_CONTENT_LENGTH));
        this.statementBuilder.append(
                StringUtil.padLeft(String.format(StringUtil.USD, this.getPurchasePriceSubtotal()), StringUtil.LEFT_PAD)
        );
        this.statementBuilder.append("\n");
//        this.statementBuilder.append("\t");
//        this.statementBuilder.append(StringUtil.padLeft("Subtotal:", BASE_CONTENT_LENGTH));
//        this.statementBuilder.append(
//                StringUtil.padLeft(String.format(StringUtil.USD, this.getSubtotal()), StringUtil.LEFT_PAD)
//        );
//        this.statementBuilder.append("\n");
        this.statementBuilder.append(
                StringUtil.padLeft(String.format("%d Items Rented", this.getRentals().size()), BASE_CONTENT_LENGTH)
        );
        if (this.transaction instanceof PercentDiscountTransaction) {
                this.statementBuilder.append(String.format(" (%s Purchase Discount!)", this.transaction));
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
