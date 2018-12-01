package MovieRental.Item;

import MovieRental.Customer.Customer;
import MovieRental.Common.XmlElement;
import MovieRental.Transaction.Transaction;
import MovieRental.Strategy.FrequentRenterPoint.FrequentRenterPointStrategy;
import MovieRental.Strategy.FrequentRenterPoint.FrequentRenterPointStrategyFactory;
import MovieRental.Strategy.Price.PriceStrategy;
import MovieRental.Strategy.Price.PriceStrategyFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class Item implements XmlElement, Comparable<Item>, Rentable, Purchasable {
    private static final int NEW_RELEASE_DAYS = 30;
    private LocalDate releaseDate;
    private Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public Customer getCustomer() {
        if (this.transaction != null) {
            return this.transaction.getCustomer();
        }

        return null;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    @Override
    public int getFrequentRentalPoints() {
        FrequentRenterPointStrategy frequentRenterPointStrategy =
                FrequentRenterPointStrategyFactory.getFrequentRentalPointStrategy(this);

        return frequentRenterPointStrategy.getFrequentRentalPoints();
    }

    @Override
    public int getDaysRented() {
        if (this.transaction != null) {
            return this.transaction.getDaysRented();
        }

        return 0;
    }

    @Override
    public double getRentalPrice() {
        return getRentalPrice(true);
    }

    @Override
    public double getRentalPrice(boolean willApplyDiscounts) {
        PriceStrategy priceStrategy = PriceStrategyFactory.getRentalPriceStrategy(this, willApplyDiscounts);

        return priceStrategy.getPrice();
    }

    @Override
    public double getPurchasePrice() {
        PriceStrategy priceStrategy = PriceStrategyFactory.getPurchasePriceStrategy(this);

        return priceStrategy.getPrice();
    }

    @Override
    public boolean isFirstRental() {
        if (this.transaction != null) {
            return this.transaction.getRentals().indexOf(this) == 0;
        }

        return false;
    }

    public boolean isNewRelease() {
        return LocalDate.now().isBefore(this.releaseDate.plusDays(this.NEW_RELEASE_DAYS));
    }

    public abstract String getRentalHeader();

    public abstract String getRentalString();

    public abstract String getPurchaseHeader();

    public abstract String getPurchaseString();

    public abstract Element getXmlList(Document doc);

    public abstract String getTitle();

    public static List<Item> getFilteredList(List<Item> items, Class... objectTypes) {
        List<Item> list = new ArrayList<>();
        Collection<Class> objectTypeCollection = new ArrayList<>(Arrays.asList(objectTypes));

        for (Item item : items) {
            if (objectTypeCollection.contains(item.getClass())) {
                list.add(item);
                continue;
            }
        }

        return list;
    }

    @Override
    public int compareTo(Item other) {
        return Double.compare(other.getRentalPrice(false), this.getRentalPrice(false));
    }
}
