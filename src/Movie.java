public class Movie extends Rental{

    enum Category {REGULAR, NEW_RELEASE, CHILDRENS};

    private String title;
    private int movieType;

    protected Movie(String title, int movieType) {
        super();
        this.title = title;
        this.movieType = movieType;
    }

    public Category getMovieType() {
        return Category.values()[this.movieType];
    }

    public double getRentalPrice() {
        return this.basePrice + (this.pricePerDay * this.getPaidRentalDays());
    }

    protected int getPaidRentalDays() {
        return Math.max(0, this.daysRented - this.freeRentalDays);
    }

    @Override
    public String toString() {
        return String.format("\t%-15s%-15s$%.2f\n", this.title, this.getMovieType(), this.getRentalPrice());
    }
}