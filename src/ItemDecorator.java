import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ItemDecorator extends Item {
    protected Item item;

    public ItemDecorator(Item item) {
        this.item = item;
    }

    @Override
    public String getTableHeader() {
        return this.item.getTableHeader();
    }

    @Override
    public Element getXmlList(Document doc) {
        return this.item.getXmlList(doc);
    }

    @Override
    public Element getXmlElement(Document doc) {
        return this.item.getXmlElement(doc);
    }

    @Override
    public String getTitle() {
        return this.item.getTitle();
    }
}
