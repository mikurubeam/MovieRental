package MovieRental.Customer;

import MovieRental.Common.XmlElement;
import MovieRental.Item.Game;
import MovieRental.Item.Movie;
import MovieRental.Transaction.StandardTransaction;
import MovieRental.Util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

        // First MovieRental.Transaction.StandardTransaction
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



//        MovieRental.Item.ItemFactory.getItem("Bambi", 10, MovieRental.Item.Movie.Category.CHILDRENS, bob);
//        MovieRental.Item.ItemFactory.getItem("Avengers", 10, MovieRental.Item.Movie.Category.NEW_RELEASE, bob);
//        MovieRental.Item.ItemFactory.getItem("Titanic", 10, MovieRental.Item.Movie.Category.REGULAR, bob);
//        MovieRental.Item.ItemFactory.getItem("Doom", 10, MovieRental.Strategy.Game.Category.GENERIC, bob);
//
//        Collections.sort(bob.getRentals());
//
//        MovieRental.Transaction.Statement.Statement bobStatementText = new MovieRental.Transaction.Statement.TextStatement(bob);
//        MovieRental.Transaction.Statement.Statement bobStatementXml = new MovieRental.Transaction.Statement.XmlStatement(bob);

//        bobStatementText.generateStatement();
//        bobStatementXml.generateStatement();
//
//        System.out.println(bobStatementText);
//        System.out.println(bobStatementXml);

//        MovieRental.Item.ItemFactory.getItem("Avengers", 2, MovieRental.Item.Movie.Category.NEW_RELEASE, sue);
//        MovieRental.Item.ItemFactory.getItem("Pinocchio", 2, MovieRental.Item.Movie.Category.CHILDRENS, sue);
//        MovieRental.Item.ItemFactory.getItem("Superman", 2, MovieRental.Item.Movie.Category.REGULAR, sue);
//        MovieRental.Item.ItemFactory.getFreeRental("Coco", 2, MovieRental.Item.Movie.Category.CHILDRENS, sue);
//
//        Collections.sort(sue.getRentals());
//
//        MovieRental.Transaction.Statement.Statement sueStatementText = new MovieRental.Transaction.Statement.TextStatement(sue);
//        MovieRental.Transaction.Statement.Statement sueStatementXml = new MovieRental.Transaction.Statement.XmlStatement(sue);
//
//        sueStatementText.generateStatement();
//        sueStatementXml.generateStatement();
//
//        System.out.println(sueStatementText);
//        System.out.println(sueStatementXml);
    }
}