public class TwentyPercentDiscountTransaction extends PercentDiscountTransaction {
    public TwentyPercentDiscountTransaction(Transaction transaction) {
        super(transaction);
        this.discount = 0.2;
    }
}
