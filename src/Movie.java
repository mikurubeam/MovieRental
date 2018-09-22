import java.util.ArrayList;
import java.util.List;

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

    public static String getStatementHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Movie Rental Summary:\n");
        stringBuilder.append("\t");
        stringBuilder.append(StringUtil.padRight("TITLE", StringUtil.RIGHT_PAD));
        stringBuilder.append(StringUtil.padRight("CATEGORY", StringUtil.RIGHT_PAD));
        stringBuilder.append(StringUtil.padRight("DAYS RENTED", StringUtil.RIGHT_PAD));
        stringBuilder.append(StringUtil.padLeft("PRICE", StringUtil.LEFT_PAD));
        stringBuilder.append("\n\n");

        return stringBuilder.toString();
    }

    public double getRentalPrice() {
        return this.basePrice + (this.pricePerDay * this.getPaidRentalDays());
    }

    protected int getPaidRentalDays() {
        return Math.max(0, this.daysRented - this.freeRentalDays);
    }

    public static List<Movie> getMovies(List<Rental> rentals) {
        List<Movie> movies = new ArrayList<>();
        for (Rental rental : rentals) {
            if (rental instanceof Movie) {
                movies.add((Movie)rental);
            }
        }

        return movies;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t");
        stringBuilder.append(StringUtil.padRight(this.title, StringUtil.RIGHT_PAD));
        stringBuilder.append(StringUtil.padRight(this.getMovieType().toString(), StringUtil.RIGHT_PAD));
        stringBuilder.append(StringUtil.padRight(String.valueOf(this.daysRented), StringUtil.RIGHT_PAD));
        stringBuilder.append(
                StringUtil.padLeft(String.format(StringUtil.USD, this.getRentalPrice()), StringUtil.LEFT_PAD)
        );
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}