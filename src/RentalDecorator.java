import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RentalDecorator extends Rental {
    protected Rental rental;

    public RentalDecorator(Rental rental) {
        this.rental = rental;
    }

    @Override
    public String getTableHeader() {
        return this.rental.getTableHeader();
    }

    @Override
    public Element getXmlList(Document doc) {
        return this.rental.getXmlList(doc);
    }

    @Override
    public Element getXmlElement(Document doc) {
        return this.rental.getXmlElement(doc);
    }

    @Override
    public String getTitle() {
        return this.rental.getTitle();
    }
}
