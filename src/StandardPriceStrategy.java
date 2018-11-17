public class StandardPriceStrategy implements PriceStrategy {
    protected int freeRentalDays;
    protected double basePrice;
    protected double pricePerDay;
    private Item item;

    public StandardPriceStrategy(Item item) {
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
}
