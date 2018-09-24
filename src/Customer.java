import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class Customer implements XmlElement{
    private String name;
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

    public static void main(String[] args) {
        Customer bob = new Customer("bob");
        Customer sue = new Customer("sue", 5);

        bob.addRental(new ChildrensMovie("Bambi", 10));
        bob.addRental(new NewRelease("Avengers", 10));
        bob.addRental(new RegularMovie("Titanic", 10));
        bob.addRental(new Game());

        Statement bobStatementText = new TextStatement(bob);
        Statement bobStatementXml = new XmlStatement(bob);

        bobStatementText.generateStatement();
        bobStatementXml.generateStatement();

        System.out.println(bobStatementText);
        System.out.println(bobStatementXml);

        sue.addRental(new ChildrensMovie("Pinocchio", 2));
        sue.addRental(new NewRelease("Avengers", 2));
        sue.addRental(new RegularMovie("Superman", 2));

        Statement sueStatementText = new TextStatement(sue);
        Statement sueStatementXml = new XmlStatement(sue);

        sueStatementText.generateStatement();
        sueStatementXml.generateStatement();

        System.out.println(sueStatementText);
        System.out.println(sueStatementXml);
    }
}