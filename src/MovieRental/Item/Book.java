package MovieRental.Item;

import MovieRental.Util.StringUtil;
import MovieRental.Util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Book extends Item {
    public enum Category {SCI_FI_FANTASY, CHILDRENS, NON_FICTION, ROMANCE}

    private String title;
    private int bookType;

    protected Book(String title, int bookType) {
        super();
        this.title = title;
        this.bookType = bookType;
    }

    public Category getBookType() {
        return Category.values()[this.bookType];
    }

    public String getRentalHeader() {
        return "Book Summary:\n" +
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
        return "Book Summary:\n" +
                StringUtil.getTableRow(
                        1,
                        2,
                        "TITLE",
                        "CATEGORY",
                        "PRICE"
                );
    }

    public Element getXmlList(Document doc) {
        return doc.createElement("books");
    }

    public Element getXmlElement(Document doc) {
        Element book = doc.createElement("book");

        XmlUtils.addChild(doc, book, "title", this.title);
        XmlUtils.addChild(doc, book, "category", this.getBookType().toString());
        XmlUtils.addChild(doc, book, "daysRented", String.valueOf(this.getTransaction().getDaysRented()));
        XmlUtils.addChild(doc, book, "Price", String.format(StringUtil.USD, this.getRentalPrice()));
        XmlUtils.addChild(doc, book, "points", String.valueOf(this.getFrequentRentalPoints()));

        return book;
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
            this.getBookType().toString(),
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
                this.getBookType().toString(),
                String.format(StringUtil.USD, this.getRentalPrice())
        );
    }
}