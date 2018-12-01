package MovieRental.Item;

import MovieRental.Customer.Customer;
import MovieRental.Strategy.Price.PriceStrategy;
import MovieRental.Strategy.Price.PriceStrategyFactory;
import MovieRental.Transaction.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ItemDecorator extends Item {
    protected Item item;

    public ItemDecorator(Item item) {
        this.item = item;
    }

    @Override
    public String getRentalHeader() {
        return this.item.getRentalHeader();
    }

    @Override
    public String getRentalString() {
        return this.item.getRentalString();
    }

    @Override
    public String getPurchaseHeader() {
        return item.getPurchaseHeader();
    }

    @Override
    public String getPurchaseString() {
        return item.getPurchaseString();
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

    @Override
    public boolean isNewRelease() {
        return this.item.isNewRelease();
    }

    @Override
    public void setTransaction(Transaction transaction) {
        this.item.setTransaction(transaction);
    }

    @Override
    public Transaction getTransaction() {
        return this.item.getTransaction();
    }

    @Override
    public boolean isFirstRental() {
        return this.item.isFirstRental();
    }

    @Override
    public Customer getCustomer() {
        return this.item.getCustomer();
    }

    @Override
    public double getRentalPrice() {
        return this.item.getRentalPrice(true);
    }

    @Override
    public double getRentalPrice(boolean willApplyDiscounts) {
        return this.item.getRentalPrice(willApplyDiscounts);
    }
}
