package MovieRental.Item;

import MovieRental.Util.StringUtil;
import MovieRental.Util.XmlUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Game extends Item {
    public enum Category {GENERIC, XBOX, PLAYSTATION, NINTENDO}

    private String title;
    private int systemType;

    public Game() {
        this.title = "Some Game";
        this.systemType = Category.GENERIC.ordinal();
    }

    public Game(String title, int gameType) {
        this.title = title;
        this.systemType = gameType;
    }

    public Category getGameSystem() {
        return Category.values()[this.systemType];
    }

    protected int getPaidRentalDays() {
        return 0;
    }

    public String getRentalHeader() {
        return "Game Summary:\n" +
                StringUtil.getTableRow(
                        1,
                        2,
                        "TITLE",
                        "SYSTEM",
                        "DAYS RENTED",
                        "PRICE",
                        "POINTS"
                );
    }

    public String getPurchaseHeader() {
        return "Game Summary:\n" +
                StringUtil.getTableRow(
                        1,
                        2,
                        "TITLE",
                        "SYSTEM",
                        "PRICE"
                );
    }

    public Element getXmlList(Document doc) {
        return doc.createElement("games");
    }

    public Element getXmlElement(Document doc) {
        Element game = doc.createElement("game");

        XmlUtils.addChild(doc, game, "title", this.title);
        XmlUtils.addChild(doc, game, "gameSystem", this.getGameSystem().toString());
        XmlUtils.addChild(doc, game, "daysRented", String.valueOf(this.getTransaction().getDaysRented()));
        XmlUtils.addChild(doc, game, "Price", String.format(StringUtil.USD, this.getRentalPrice()));
        XmlUtils.addChild(doc, game, "points", String.valueOf(this.getFrequentRentalPoints()));

        return game;
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
                this.getGameSystem().toString(),
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
                this.getGameSystem().toString(),
                String.format(StringUtil.USD, this.getRentalPrice())
        );
    }
}
