package MovieRental.Item;

import MovieRental.Util.StringUtil;
import MovieRental.Util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CompactDisc extends Item {
    public enum Category {SINGLE, ALBUM, AUDIO_BOOK}

    private String title;
    private int cdType;

    protected CompactDisc(String title, int cdType) {
        super();
        this.title = title;
        this.cdType = cdType;
    }

    public Category getCDType() {
        return Category.values()[this.cdType];
    }

    public String getRentalHeader() {
        return "Compact Disc Summary:\n" +
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
        return "Compact Disc Summary:\n" +
                StringUtil.getTableRow(
                        1,
                        2,
                        "TITLE",
                        "CATEGORY",
                        "PRICE"
                );
    }

    public Element getXmlList(Document doc) {
        return doc.createElement("compactDiscs");
    }

    public Element getXmlElement(Document doc) {
        Element movie = doc.createElement("compactDisc");

        XmlUtils.addChild(doc, movie, "title", this.title);
        XmlUtils.addChild(doc, movie, "category", this.getCDType().toString());
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
            this.title,
            this.getCDType().toString(),
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
                this.title,
                this.getCDType().toString(),
                String.format(StringUtil.USD, this.getRentalPrice())
        );
    }
}