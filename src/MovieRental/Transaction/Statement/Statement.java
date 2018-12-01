package MovieRental.Transaction.Statement;

import MovieRental.Common.*;
import MovieRental.Customer.Customer;
import MovieRental.Item.*;
import MovieRental.Transaction.Transaction;
import MovieRental.Transaction.TransactionDecorator;

public abstract class Statement extends TransactionDecorator implements Printable {
    protected final Customer customer;

    protected Statement(Transaction transaction) {
        super(transaction);
        this.customer = transaction.getCustomer();
    }

    public void generateStatement() {
        this.addStatementHeader();

        this.addStatementBody();

        this.addStatementFooter();
    }

    public void addStatementBody() {
        if (this.transaction.getRentals().size() > 0) {
            this.addRentalHeader();
            this.addRentalSummaryByType(Item.getFilteredList(this.getRentals(), Movie.class, Dvd.class));
            this.addRentalSummaryByType(Item.getFilteredList(this.getRentals(), Game.class));
            this.addRentalSummaryByType(Item.getFilteredList(this.getRentals(), CompactDisc.class));
            this.addRentalSummaryByType(Item.getFilteredList(this.getRentals(), Book.class));
        }

        if (this.transaction.getPurchases().size() > 0) {
            this.addPurchaseHeader();
            this.addPurchaseSummaryByType(Item.getFilteredList(this.getPurchases(), Movie.class, Dvd.class));
            this.addPurchaseSummaryByType(Item.getFilteredList(this.getPurchases(), Game.class));
            this.addPurchaseSummaryByType(Item.getFilteredList(this.getPurchases(), CompactDisc.class));
            this.addPurchaseSummaryByType(Item.getFilteredList(this.getPurchases(), Book.class));
        }
    }

    public Statement getDiscountStatement(Transaction transaction) {
        if (this instanceof XmlStatement) {
            return new XmlStatement(transaction);
        }

        // Text by default
        return new TextStatement(transaction);
    }
}
