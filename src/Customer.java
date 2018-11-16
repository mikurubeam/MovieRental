import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer implements XmlElement{
    private String name;
    private int age;
    private List<Rental> rentals;
    private int frequentRenterPoints;

    public Customer (String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
        this.frequentRenterPoints = 0;
    }

    public Customer (String name, int frequentRenterPoints) {
        this(name);
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    public List<Rental> getRentals() {
        return this.rentals;
    }

    public int getFrequentRenterPoints() {
        return this.frequentRenterPoints;
    }

    public Element getXmlElement(Document doc) {
        Element customer = doc.createElement("customer");

        XmlUtils.addChild(doc, customer, "name", this.name);

        Element frequentRenterPoints = doc.createElement("frequentRenterPoints");

        XmlUtils.addChild(doc, frequentRenterPoints, "current", String.valueOf(this.frequentRenterPoints));

        customer.appendChild(frequentRenterPoints);

        return customer;
    }

    public List<Movie.Category> getRentedMovieCategories() {
        Map<Movie.Category, Integer> movieTypes = new HashMap<>();
        for (Rental rental : Rental.getFilteredList(this.rentals, Movie.class)) {
            movieTypes.put(((Movie)rental).getMovieType(), 1);
        }

        return new ArrayList<>(movieTypes.keySet());
    }

    public boolean hasMultipleMovieCategories() {
        return this.hasMultipleMovieCategories(1);
    }

    public boolean hasMultipleMovieCategories(int min) {
        return this.getRentedMovieCategories().size() > min;
    }

    public boolean hasNewReleases() {
        for (Rental rental : Rental.getFilteredList(this.rentals, Movie.class)) {
            if (((Movie)rental).getMovieType() == Movie.Category.NEW_RELEASE) {
                return true;
            }
        }

        return false;
    }

    public boolean isInAgeRange(int min, int max) {
        return this.age >= min && this.age <= max;
    }

    public static void main(String[] args) {
        Customer bob = new Customer("bob");
        bob.setAge(35);
        Customer sue = new Customer("sue", 5);
        sue.setAge(21);
        Customer jack = new Customer("jack");
        jack.setAge(17);
        Customer jill = new Customer("jill");
        jill.setAge(19);

        RentalFactory.getRental("Bambi", 10, Movie.Category.CHILDRENS, bob);
        RentalFactory.getRental("Avengers", 10, Movie.Category.NEW_RELEASE, bob);
        RentalFactory.getRental("Titanic", 10, Movie.Category.REGULAR, bob);
        RentalFactory.getRental("Doom", 10, Game.Category.GENERIC, bob);

        Statement bobStatementText = new TextStatement(bob);
        Statement bobStatementXml = new XmlStatement(bob);

        bobStatementText.generateStatement();
        bobStatementXml.generateStatement();

        System.out.println(bobStatementText);
        System.out.println(bobStatementXml);

        RentalFactory.getRental("Avengers", 2, Movie.Category.NEW_RELEASE, sue);
        RentalFactory.getRental("Pinocchio", 2, Movie.Category.CHILDRENS, sue);
        RentalFactory.getRental("Superman", 2, Movie.Category.REGULAR, sue);

        Statement sueStatementText = new TextStatement(sue);
        Statement sueStatementXml = new XmlStatement(sue);

        sueStatementText.generateStatement();
        sueStatementXml.generateStatement();

        System.out.println(sueStatementText);
        System.out.println(sueStatementXml);
    }
}