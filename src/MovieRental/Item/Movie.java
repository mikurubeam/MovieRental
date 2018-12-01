package MovieRental.Item;

import MovieRental.Util.StringUtil;
import MovieRental.Util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Movie extends Item {
    public enum Category {REGULAR, CHILDRENS}

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

    public String getRentalHeader() {
        return "Movie Summary:\n" +
                StringUtil.getTableRow(
                        1,
                        2,
                        "TITLE",
                        "CATEGORY",
                        "DAYS RENTED",
                        "PRICE",
                        "POINTS"
                );
    }

    public String getPurchaseHeader() {
        return "Movie Summary:\n" +
                StringUtil.getTableRow(
                        1,
                        2,
                        "TITLE",
                        "CATEGORY",
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
        XmlUtils.addChild(doc, movie, "daysRented", String.valueOf(this.getTransaction().getDaysRented()));
        XmlUtils.addChild(doc, movie, "Price", String.format(StringUtil.USD, this.getRentalPrice()));
        XmlUtils.addChild(doc, movie, "points", String.valueOf(this.getFrequentRentalPoints()));

        return movie;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getRentalString() {
        return StringUtil.getTableRow(
                1,
                1,
                this.getTitle(),
                this.getMovieType().toString(),
                String.valueOf(this.getDaysRented()),
                String.format(StringUtil.USD, this.getRentalPrice()),
                String.valueOf(this.getFrequentRentalPoints())
        );
    }

    @Override
    public String getPurchaseString() {
        return StringUtil.getTableRow(
                1,
                1,
                this.getTitle(),
                this.getMovieType().toString(),
                String.format(StringUtil.USD, this.getPurchasePrice())
        );
    }
}