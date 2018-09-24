import java.util.List;

public abstract class Statement {
    protected Customer customer;
    protected double totalAmount;
    protected int frequentRenterPoints;

    protected Statement(Customer customer) {
        this.customer = customer;
        this.totalAmount = 0;
        this.frequentRenterPoints = 0;
    }

    protected int getTotalFrequentRenterPoints() {
        return this.frequentRenterPoints + this.customer.getFrequentRenterPoints();
    }

    public void generateStatement() {
        this.addStatementHeaderData();

        this.addRentalSummaryByType(Rental.getFilteredList(this.customer.getRentals(), Movie.class));
        this.addRentalSummaryByType(Rental.getFilteredList(this.customer.getRentals(), Game.class));

        this.addStatementFooterData();
    }

    protected abstract void addStatementHeaderData();

    protected abstract void addRentalSummaryByType(List<Rental> rentals);

    protected abstract void addStatementFooterData();
}
