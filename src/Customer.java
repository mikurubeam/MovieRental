import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals;
    private int frequentRenterPoints;
    private String statement;
    
    public Customer (String name) {
        this.name = name;
        this.rentals = new ArrayList<Rental>();
        this.frequentRenterPoints = 0;
    }

    public Customer (String name, int frequentRenterPoints) {
        this(name);
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public void setfrequentRenterPoints(int frequentRenterPoints) {
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public void addFrequentRenterPoints(int frequentRenterPoints) {
        this.frequentRenterPoints += frequentRenterPoints;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }
    
    public String getName() {
        return this.name;
    }

    public String getStatement() {
        return this.statement;
    }
    
    public void generateStatement() {
    
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(String.format("Rental Record for %s:\n", this.name));

        for (Rental rental : this.rentals) {
            totalAmount += rental.getRentalPrice();
            frequentRenterPoints += rental.getBonusPoints();
            resultBuilder.append(rental);
        }

        this.addFrequentRenterPoints(frequentRenterPoints);

        // add footer lines
        resultBuilder.append(String.format("%30s    $%.2f\n", "Amount owed is:", totalAmount));
        resultBuilder.append(String.format("\nYou earned %d frequent renter points!\n", frequentRenterPoints));
        resultBuilder.append(String.format("Total frequent renter points: %d\n", this.frequentRenterPoints));
        this.statement = resultBuilder.toString();
    }

    public static void main(String[] args) {
        Customer bob = new Customer("bob");
        Customer sue = new Customer("sue", 5);

        bob.addRental(new ChildrensMovie("Bambi", 10));
        bob.addRental(new NewRelease("Avengers", 10));
        bob.addRental(new RegularMovie("Titanic", 10));
        bob.generateStatement();

        sue.addRental(new ChildrensMovie("Pinocchio", 2));
        sue.addRental(new NewRelease("Avengers", 2));
        sue.addRental(new RegularMovie("Superman", 2));
        sue.generateStatement();

        System.out.println(bob.getStatement());
        System.out.println(sue.getStatement());
    }
}