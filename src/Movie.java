public abstract class Movie extends Rental {
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

    public String getTableHeader() {
        return "Movie Rental Summary:\n" +
            StringUtil.getTableRow(
                1,
                2,
                "TITLE",
                "CATEGORY",
                "DAYS RENTED",
                "PRICE"
            );
    }

    @Override
    public String toString() {
        return StringUtil.getTableRow(
            1,
            1,
            this.title,
            this.getMovieType().toString(),
            String.valueOf(this.daysRented),
            String.format(StringUtil.USD, this.getRentalPrice())
        );
    }
}