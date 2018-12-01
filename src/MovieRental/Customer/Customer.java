package MovieRental.Customer;

import MovieRental.Common.XmlElement;
import MovieRental.Item.*;
import MovieRental.Transaction.StandardTransaction;
import MovieRental.Util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.LocalDate;
import java.time.Month;

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
        Movie bambi = ItemFactory.getItem("Bambi", Movie.Category.CHILDRENS, LocalDate.of(2005, Month.MARCH, 1));
        Movie avengers = ItemFactory.getItem("Avengers", Movie.Category.REGULAR, LocalDate.of(2015, Month.OCTOBER, 2));
        Movie titanic = ItemFactory.getItem("Titanic", Movie.Category.REGULAR, LocalDate.of(1999, Month.AUGUST, 31));
        Dvd titanicDvd = ItemFactory.getItem(titanic);
        Movie incredibles2 = ItemFactory.getItem("Incredibles 2", Movie.Category.CHILDRENS, LocalDate.of(2018, Month.NOVEMBER, 6));
        Movie someNewMovie = ItemFactory.getItem("New Movie", Movie.Category.REGULAR, LocalDate.now().minusDays(2));
        Movie someNewChildrensMovie = ItemFactory.getItem("New Childrens Movie", Movie.Category.REGULAR, LocalDate.now().minusDays(2));
        Dvd someNewMovieDvd = ItemFactory.getItem(someNewMovie);
        Dvd someNewChildrensMovieDvd = ItemFactory.getItem(someNewChildrensMovie);

        Customer bob = new Customer("bob", 7);
        bob.setAge(21);
        Customer sue = new Customer("sue", 5);
        sue.setAge(35);

        // First MovieRental.Transaction.StandardTransaction
        StandardTransaction bobTxn = new StandardTransaction(bob);
        bobTxn.setDaysRented(10);

        bobTxn.addRental(bambi);
        bobTxn.addRental(avengers);
        bobTxn.addRental(titanicDvd);
        bobTxn.addRental(incredibles2);
        bobTxn.addRental(someNewMovie);
        bobTxn.addRental(someNewMovieDvd);
        bobTxn.addPurchase(someNewChildrensMovie);
        bobTxn.addPurchase(someNewChildrensMovieDvd);

//        bobTxn.printStatementAsText();
//        bobTxn.printStatementAsXML();
        bobTxn.completeTransaction();

        // New standardTransaction
        bobTxn = new StandardTransaction(bob);
        bobTxn.setDaysRented(5);

        Movie coco = ItemFactory.getItem("Coco", Movie.Category.CHILDRENS, LocalDate.of(2018, Month.FEBRUARY, 27));
        Dvd cocoDvd = ItemFactory.getItem(coco);
        Movie pinocchio = ItemFactory.getItem("Pinocchio", Movie.Category.CHILDRENS, LocalDate.of(1999, Month.OCTOBER, 29));
        Movie superman = ItemFactory.getItem("Superman", Movie.Category.REGULAR, LocalDate.of(2001, Month.SEPTEMBER, 10));
        Movie blackPanther = ItemFactory.getItem("Black Panther", Movie.Category.REGULAR, LocalDate.of(2018, Month.MAY, 15));
        Dvd incredibles2Dvd = ItemFactory.getItem(incredibles2);

        bobTxn.addRental(cocoDvd);
        bobTxn.addRental(pinocchio);
        bobTxn.addRental(superman);
//        bobTxn.addRental(blackPanther);
//        bobTxn.addRental(incredibles2Dvd);
        bobTxn.addPurchase(titanicDvd);
        bobTxn.addPurchase(blackPanther);
        bobTxn.addPurchase(incredibles2Dvd);

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