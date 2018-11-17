import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class Rental implements XmlElement, Comparable<Rental> {
    protected int daysRented;
    private Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public Customer getCustomer() {
        return this.transaction.getCustomer();
    }

    public int getFrequentRentalPoints() {
        FrequentRenterPointStrategy frequentRenterPointStrategy =
                FrequentRenterPointStrategyFactory.getFrequentRentalPointStrategy(this);

        return frequentRenterPointStrategy.getFrequentRentalPoints();
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return this.daysRented;
    }

    public double getRentalPrice() {
        return getRentalPrice(true);
    }

    public double getRentalPrice(boolean willApplyDiscounts) {
        PriceStrategy priceStrategy = PriceStrategyFactory.getPriceStrategy(this, willApplyDiscounts);

        return priceStrategy.getPrice();
    }

    public boolean isFirstRental() {
        return this.transaction.getRentals().indexOf(this) == 0;
    }

    public abstract String getTableHeader();

    public abstract Element getXmlList(Document doc);

    public abstract String getTitle();

    public static <T> List<Rental> getFilteredList(List<Rental> rentals, Class<T> objectType) {
        List<Rental> list = new ArrayList<>();

        for (Rental rental : rentals) {
            if (objectType.isInstance(rental)) {
                list.add(rental);
            }
        }

        return list;
    }

    @Override
    public int compareTo(Rental other) {
        return Double.compare(other.getRentalPrice(false), this.getRentalPrice(false));
    }
}
