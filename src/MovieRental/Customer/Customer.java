package MovieRental.Customer;

import MovieRental.Common.XmlElement;
import MovieRental.Item.*;
import MovieRental.Transaction.StandardTransaction;
import MovieRental.Transaction.Transaction;
import MovieRental.Util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Customer implements XmlElement {
    private String name;
    private int age;
    private int frequentRenterPoints;
    List<Transaction> transactions;

    public Customer(String name) {
        this.name = name;
        this.frequentRenterPoints = 0;
        this.transactions = new ArrayList<>();
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

    public List<Transaction> getTransactionHistory() {
        return this.transactions;
    }

    public void archiveTransaction(Transaction transaction) {
        this.frequentRenterPoints = transaction.getTotalFrequentRenterPoints();
        this.transactions.add(transaction);
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
        // "Database" of items
        Book someBook = ItemFactory.getItem("Some Book", Book.Category.NON_FICTION, LocalDate.now().minusDays(100));
        someBook.setPurchasePrice(35);
        CompactDisc someAlbum = ItemFactory.getItem("Some Album", CompactDisc.Category.ALBUM, LocalDate.now().minusDays(15));
        someAlbum.setPurchasePrice(15);
        CompactDisc someSingle = ItemFactory.getItem("Some Single", CompactDisc.Category.SINGLE, LocalDate.now().minusDays(15));
        someSingle.setPurchasePrice(10);
        Game someGame = ItemFactory.getItem("Some Game", Game.Category.PLAYSTATION, LocalDate.now().minusDays(8));
        someGame.setPurchasePrice(40);
        Movie bambi = ItemFactory.getItem("Bambi", Movie.Category.CHILDRENS, LocalDate.of(2005, Month.MARCH, 1));
        bambi.setPurchasePrice(5);
        Movie avengers = ItemFactory.getItem("Avengers", Movie.Category.REGULAR, LocalDate.of(2015, Month.OCTOBER, 2));
        avengers.setPurchasePrice(10);
        Movie titanic = ItemFactory.getItem("Titanic", Movie.Category.REGULAR, LocalDate.of(1999, Month.AUGUST, 31));
        titanic.setPurchasePrice(5);
        Dvd titanicDvd = ItemFactory.getItem(titanic);
        titanicDvd.setPurchasePrice(7.5);
        Movie incredibles2 = ItemFactory.getItem("Incredibles 2", Movie.Category.CHILDRENS, LocalDate.of(2018, Month.NOVEMBER, 6));
        incredibles2.setPurchasePrice(15);
        Dvd incredibles2Dvd = ItemFactory.getItem(incredibles2);
        incredibles2Dvd.setPurchasePrice(20);
        Movie coco = ItemFactory.getItem("Coco", Movie.Category.CHILDRENS, LocalDate.of(2018, Month.FEBRUARY, 27));
        coco.setPurchasePrice(15);
        Dvd cocoDvd = ItemFactory.getItem(coco);
        cocoDvd.setPurchasePrice(20);
        Movie pinocchio = ItemFactory.getItem("Pinocchio", Movie.Category.CHILDRENS, LocalDate.of(1999, Month.OCTOBER, 29));
        pinocchio.setPurchasePrice(5);
        Movie superman = ItemFactory.getItem("Superman", Movie.Category.REGULAR, LocalDate.of(2001, Month.SEPTEMBER, 10));
        superman.setPurchasePrice(8);
        Movie blackPanther = ItemFactory.getItem("Black Panther", Movie.Category.REGULAR, LocalDate.of(2018, Month.MAY, 15));
        blackPanther.setPurchasePrice(15);
        Movie someNewMovie = ItemFactory.getItem("New Movie", Movie.Category.REGULAR, LocalDate.now().minusDays(2));
        someNewMovie.setPurchasePrice(17);
        Dvd someNewMovieDvd = ItemFactory.getItem(someNewMovie);
        someNewMovieDvd.setPurchasePrice(25);
        Movie someNewChildrensMovie = ItemFactory.getItem("New Childrens Movie", Movie.Category.REGULAR, LocalDate.now().minusDays(2));
        someNewChildrensMovie.setPurchasePrice(12.5);
        Dvd someNewChildrensMovieDvd = ItemFactory.getItem(someNewChildrensMovie);
        someNewChildrensMovieDvd.setPurchasePrice(17.5);

        Customer bob = new Customer("bob", 7);
        bob.setAge(21);

        // First Transaction
        StandardTransaction bobTxn = new StandardTransaction(bob);
        bobTxn.setDaysRented(10);

        bobTxn.addRental(bambi);
        bobTxn.addRental(avengers);
        bobTxn.addRental(titanicDvd);
        bobTxn.addRental(incredibles2);
        bobTxn.addRental(someNewMovie);
        bobTxn.addRental(someBook);
        bobTxn.addRental(someAlbum);
        bobTxn.addRental(someSingle);
        bobTxn.addRental(someGame);
        bobTxn.addPurchase(someNewChildrensMovie);
        bobTxn.addPurchase(someNewChildrensMovieDvd);
        // Simulate accidentally adding the same item to the cart
        bobTxn.addPurchase(someNewChildrensMovieDvd);
        bobTxn.removePurchase(someNewChildrensMovieDvd);

//        bobTxn.printStatementAsText();
//        bobTxn.printStatementAsXML();
        bobTxn.applyDiscounts();
        bobTxn.completeTransaction();

        // Second Transaction
        bobTxn = new StandardTransaction(bob);
        bobTxn.setDaysRented(5);

        bobTxn.addRental(cocoDvd);
        bobTxn.addRental(pinocchio);
        bobTxn.addRental(superman);
        bobTxn.addPurchase(titanicDvd);
        bobTxn.addPurchase(blackPanther);
        bobTxn.addPurchase(incredibles2Dvd);

//        bobTxn.printStatementAsText();
//        bobTxn.printStatementAsXML();
        bobTxn.applyDiscounts();
        bobTxn.completeTransaction();
    }
}