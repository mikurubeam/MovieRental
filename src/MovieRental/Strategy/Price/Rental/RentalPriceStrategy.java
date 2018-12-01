package MovieRental.Strategy.Price.Rental;
import MovieRental.Item.Item;
import MovieRental.Customer.Customer;
import MovieRental.Strategy.Price.PriceStrategy;
import MovieRental.Transaction.Transaction;

public class RentalPriceStrategy implements PriceStrategy {
    protected int freeRentalDays;
    protected double basePrice;
    protected double pricePerDay;
    private Item item;

    public RentalPriceStrategy(Item item) {
        this.freeRentalDays = 0;
        this.basePrice = 0;
        this.pricePerDay = 0;
        this.item = item;
    }

    public double getPrice() {
        return this.basePrice + (this.pricePerDay * this.getPaidRentalDays());
    }

    public Item getItem() {
        return this.item;
    }

    public Customer getCustomer() {
        return this.item.getCustomer();
    }

    protected int getPaidRentalDays() {
        return Math.max(0, this.item.getDaysRented() - this.freeRentalDays);
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getPricePerDay() {
        return this.pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }


}
