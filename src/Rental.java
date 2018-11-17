import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class Rental implements XmlElement, Comparable<Rental> {
    protected int daysRented;
    private Customer customer;
    private PriceStrategy priceStrategy;
    private FrequentRenterPointStrategy frequentRenterPointStrategy;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public int getFrequentRentalPoints() {
        this.frequentRenterPointStrategy = FrequentRenterPointStrategyFactory.getFrequentRentalPointStrategy(this);

        return this.frequentRenterPointStrategy.getFrequentRentalPoints();
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
        this.priceStrategy = PriceStrategyFactory.getPriceStrategy(this, willApplyDiscounts);

        return this.priceStrategy.getPrice();
    }

    public boolean isFirstRental() {
        return this.customer.getRentals().indexOf(this) == 0;
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
