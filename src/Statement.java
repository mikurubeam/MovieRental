import java.util.List;

public abstract class Statement {
    protected Transaction transaction;
    protected Customer customer;
    protected double totalAmount;

    protected Statement(Transaction transaction) {
        this.transaction = transaction;
        this.customer = transaction.getCustomer();
    }

    protected Customer getCustomer() {
        return this.customer;
    }

    public int getTotalFrequentRenterPoints() {
        return this.customer.getFrequentRenterPoints()
                + this.getEarnedFrequentRenterPoints()
                - this.getSpentFrequentRenterPoints();
    }

    public void generateStatement() {
        this.addStatementHeaderData();

        this.addRentalSummaryByType(Rental.getFilteredList(this.customer.getRentals(), Movie.class));
        this.addRentalSummaryByType(Rental.getFilteredList(this.customer.getRentals(), Game.class));

        this.addStatementFooterData();
    }

    protected double getTotalPrice() {
        return this.transaction.getTotalPrice();
    }

    public int getEarnedFrequentRenterPoints() {
        return this.transaction.getEarnedFrequentRenterPoints();
    }

    public int getSpentFrequentRenterPoints() {
        return this.transaction.getSpentFrequentRenterPoints();
    }

    protected abstract void addStatementHeaderData();

    protected abstract void addRentalSummaryByType(List<Rental> rentals);

    protected abstract void addStatementFooterData();
}
