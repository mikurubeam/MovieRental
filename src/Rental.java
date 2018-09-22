public abstract class Rental {
    protected int daysRented;
    protected int freeRentalDays;
    protected double basePrice;
    protected double pricePerDay;
    protected int bonusPoints;

    protected Rental() {
        this.bonusPoints = 1;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public abstract double getRentalPrice();

    protected abstract int getPaidRentalDays();


}