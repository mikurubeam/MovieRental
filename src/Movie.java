import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    public Element getXmlList(Document doc) {
        return doc.createElement("movies");
    }

    public Element getXmlElement(Document doc) {
        Element movie = doc.createElement("movie");

        XmlUtils.addChild(doc, movie, "title", this.title);
        XmlUtils.addChild(doc, movie, "category", this.getMovieType().toString());
        XmlUtils.addChild(doc, movie, "daysRented", String.valueOf(this.daysRented));
        XmlUtils.addChild(doc, movie, "price", String.format(StringUtil.USD, this.getRentalPrice()));

        return movie;
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