package MovieRental.Common;

import MovieRental.Item.Item;

import java.util.List;

public interface Printable {

    default void generateStatement() {
        this.addStatementHeader();
        this.addStatementBody();
        this.addStatementFooter();
    }

    void addStatementHeader();

    void addStatementBody();

    void addRentalSummaryByType(List<Item> items);

    void addStatementFooter();

    void addPurchaseSummaryByType(List<Item> items);

    void addPurchaseHeader();

    void addRentalHeader();
}
