public class RegularMovie extends Movie {
    public RegularMovie(String title, int daysRented) {
        super(title, Category.REGULAR.ordinal());
        this.daysRented = daysRented;
        this.freeRentalDays = 2;
        this.basePrice = 2;
        this.pricePerDay = 1.5;
    }
}
