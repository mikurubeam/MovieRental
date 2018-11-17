public abstract class Statement extends TransactionDecorator implements Printable {
    protected final Customer customer;

    protected Statement(Transaction transaction) {
        super(transaction);
        this.customer = transaction.getCustomer();
    }

    public void generateStatement() {
        this.addStatementHeader();

        this.addStatementBody();

        this.addStatementFooter();
    }

    public void addStatementBody() {
        this.addRentalSummaryByType(Item.getFilteredList(this.getRentals(), Movie.class));
        this.addRentalSummaryByType(Item.getFilteredList(this.getRentals(), Game.class));
    }

    public Statement getDiscountStatement(Transaction transaction) {
        if (this instanceof XmlStatement) {
            return new XmlStatement(transaction);
        }

        // Text by default
        return new TextStatement(transaction);
    }
}
