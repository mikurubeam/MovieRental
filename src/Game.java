import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Game extends Rental {
    enum Category {GENERIC, XBOX, PLAYSTATION, NINTENDO};

    private String title;
    private int systemType;

    public Game() {
        this.title = "Some Game";
        this.systemType = Category.GENERIC.ordinal();
    }

    public Category getGameSystem() {
        return Category.values()[this.systemType];
    }

    public double getRentalPrice() {
        return 0;
    }

    protected int getPaidRentalDays() {
        return 0;
    }

    public String getTableHeader() {
        return "Game Rental Summary:\n" +
            StringUtil.getTableRow(
                1,
                2,
                "TITLE",
                "SYSTEM",
                "DAYS RENTED",
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
        XmlUtils.addChild(doc, game, "daysRented", String.valueOf(this.daysRented));
        XmlUtils.addChild(doc, game, "price", String.format(StringUtil.USD, this.getRentalPrice()));

        return game;
    }

    @Override
    public String toString() {
        return StringUtil.getTableRow(
                1,
                1,
                this.title,
                this.getGameSystem().toString(),
                String.valueOf(this.daysRented),
                String.format(StringUtil.USD, this.getRentalPrice())
        );
    }
}
