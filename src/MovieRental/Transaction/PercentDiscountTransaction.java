package MovieRental.Transaction;

public class PercentDiscountTransaction extends TransactionDecorator {
    double discount;
    public PercentDiscountTransaction(Transaction transaction)
    {
        super(transaction);
        this.discount = 0;
    }

    public double getPurchasePriceTotal() {
        return super.getPurchasePriceSubtotal() * (1 - this.discount);
    }

    public double getTotalPrice() {
        return this.getRentalPriceTotal() + this.getPurchasePriceTotal();
    }

    @Override
    public String toString() {
        return String.format("%.1f%%", (100 * this.discount));
    }
}
