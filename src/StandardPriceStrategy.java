public class StandardPriceStrategy implements PriceStrategy {
    protected int freeRentalDays;
    protected double basePrice;
    protected double pricePerDay;
    private Rental rental;

    public StandardPriceStrategy(Rental rental) {
        this.freeRentalDays = 0;
        this.basePrice = 0;
        this.pricePerDay = 0;
        this.rental = rental;
    }

    public double getPrice() {
        return this.basePrice + (this.pricePerDay * this.getPaidRentalDays());
    }

    public Rental getRental() {
        return this.rental;
    }

    public Customer getCustomer() {
        return this.rental.getCustomer();
    }

    protected int getPaidRentalDays() {
        return Math.max(0, this.rental.getDaysRented() - this.freeRentalDays);
    }
}
