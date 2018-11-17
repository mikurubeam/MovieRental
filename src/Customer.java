import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.*;

public class Customer implements XmlElement {
    private String name;
    private int age;
    private int frequentRenterPoints;

    public Customer(String name) {
        this.name = name;
        this.frequentRenterPoints = 0;
    }

    public Customer (String name, int frequentRenterPoints) {
        this(name);
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public boolean isEligibleForFreeRental() {
        return this.frequentRenterPoints >= 10;
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

    public int getFrequentRenterPoints() {
        return this.frequentRenterPoints;
    }

    public void setFrequentRenterPoints(int frequentRenterPoints) {
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public Element getXmlElement(Document doc) {
        Element customer = doc.createElement("customer");

        XmlUtils.addChild(doc, customer, "name", this.name);

        Element frequentRenterPoints = doc.createElement("frequentRenterPointsEarned");

        XmlUtils.addChild(doc, frequentRenterPoints, "current", String.valueOf(this.frequentRenterPoints));

        customer.appendChild(frequentRenterPoints);

        return customer;
    }

    public boolean isInAgeRange(int min, int max) {
        return this.age >= min && this.age <= max;
    }

    public static void main(String[] args) {
        Customer bob = new Customer("bob", 7);
        bob.setAge(21);
        Customer sue = new Customer("sue", 5);
        sue.setAge(35);

        // First StandardTransaction
        StandardTransaction bobTxn = new StandardTransaction(bob);
        bobTxn.setDaysRented(10);

        bobTxn.addRental("Bambi", Movie.Category.CHILDRENS);
        bobTxn.addRental("Avengers", Movie.Category.NEW_RELEASE);
        bobTxn.addRental("Titanic", Movie.Category.REGULAR);
        bobTxn.addRental("Doom", Game.Category.GENERIC);

//        bobTxn.printStatementAsText();
//        bobTxn.printStatementAsXML();
        bobTxn.completeTransaction();

        // New standardTransaction
        bobTxn = new StandardTransaction(bob);
        bobTxn.setDaysRented(5);

        bobTxn.addRental("Coco", Movie.Category.NEW_RELEASE);
        bobTxn.addRental("Pinocchio", Movie.Category.CHILDRENS);
        bobTxn.addRental("Superman", Movie.Category.REGULAR);
        bobTxn.addRental("Black Panther", Movie.Category.NEW_RELEASE);
        bobTxn.addRental("Incredibles 2", Movie.Category.NEW_RELEASE);
        bobTxn.addRental("Titanic", Movie.Category.REGULAR);

//        bobTxn.printStatementAsText();
//        bobTxn.printStatementAsXML();
        bobTxn.completeTransaction();



//        RentalFactory.getItem("Bambi", 10, Movie.Category.CHILDRENS, bob);
//        RentalFactory.getItem("Avengers", 10, Movie.Category.NEW_RELEASE, bob);
//        RentalFactory.getItem("Titanic", 10, Movie.Category.REGULAR, bob);
//        RentalFactory.getItem("Doom", 10, Game.Category.GENERIC, bob);
//
//        Collections.sort(bob.getRentals());
//
//        Statement bobStatementText = new TextStatement(bob);
//        Statement bobStatementXml = new XmlStatement(bob);

//        bobStatementText.generateStatement();
//        bobStatementXml.generateStatement();
//
//        System.out.println(bobStatementText);
//        System.out.println(bobStatementXml);

//        RentalFactory.getItem("Avengers", 2, Movie.Category.NEW_RELEASE, sue);
//        RentalFactory.getItem("Pinocchio", 2, Movie.Category.CHILDRENS, sue);
//        RentalFactory.getItem("Superman", 2, Movie.Category.REGULAR, sue);
//        RentalFactory.getFreeRental("Coco", 2, Movie.Category.CHILDRENS, sue);
//
//        Collections.sort(sue.getRentals());
//
//        Statement sueStatementText = new TextStatement(sue);
//        Statement sueStatementXml = new XmlStatement(sue);
//
//        sueStatementText.generateStatement();
//        sueStatementXml.generateStatement();
//
//        System.out.println(sueStatementText);
//        System.out.println(sueStatementXml);
    }
}