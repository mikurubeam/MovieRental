import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals;
    private int frequentRenterPoints;
    private Statement statement;
    
    public Customer (String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
        this.frequentRenterPoints = 0;
        this.statement = null;
    }

    public Customer (String name, int frequentRenterPoints) {
        this(name);
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public String getName() {
        return this.name;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    public int getFrequentRenterPoints() {
        return this.frequentRenterPoints;
    }

    public String getStatement() {
        return this.statement.toString();
    }
    
    public void generateStatement() {
        this.statement = new Statement(this);
        this.statement.addRentalSummary(this.rentals);
        this.statement.addStatementFooter();
    }

    public static void main(String[] args) {
        Customer bob = new Customer("bob");
        Customer sue = new Customer("sue", 5);

        bob.addRental(new ChildrensMovie("Bambi", 10));
        bob.addRental(new NewRelease("Avengers", 10));
        bob.addRental(new RegularMovie("Titanic", 10));
        bob.addRental(new Game());
        bob.generateStatement();

        sue.addRental(new ChildrensMovie("Pinocchio", 2));
        sue.addRental(new NewRelease("Avengers", 2));
        sue.addRental(new RegularMovie("Superman", 2));
        sue.generateStatement();

        System.out.println(bob.getStatement());
        System.out.println(sue.getStatement());
    }
}