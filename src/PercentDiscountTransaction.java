public class PercentDiscountTransaction extends TransactionDecorator {
    double discount;
    public PercentDiscountTransaction(Transaction transaction)
    {
        super(transaction);
        this.discount = 0;
    }

    public double getTotalPrice() {
        return super.getTotalPrice() * (1 - this.discount);
    }

    public double getDiscount() {
        return this.discount;
    }

    @Override
    public String toString() {
        return String.format("%.1f%%", (100 * this.discount));
    }
}
