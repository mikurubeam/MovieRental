public class ChildrensMovie extends Movie{
    public ChildrensMovie(String title, int daysRented) {
        super(title, Category.CHILDRENS.ordinal());
        this.daysRented = daysRented;
        this.freeRentalDays = 3;
        this.basePrice = 1.5;
        this.pricePerDay = 1.5;
    }
}
