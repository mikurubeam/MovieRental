package MovieRental.Transaction;

public class FiftyPercentDiscountTransaction extends PercentDiscountTransaction {
    public FiftyPercentDiscountTransaction(Transaction transaction) {
        super(transaction);
        this.discount = 0.5;
    }
}
