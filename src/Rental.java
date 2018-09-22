public abstract class Rental {
    protected int daysRented;
    protected int freeRentalDays;
    protected double basePrice;
    protected double pricePerDay;
    protected int bonusPoints;

    protected Rental() {
        this.bonusPoints = 1;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public int getFreeRentalDays() {
        return freeRentalDays;
    }

    protected void setFreeRentalDays(int freeRentalDays) {
        this.freeRentalDays = freeRentalDays;
    }

    public double getBasePrice() {
        return basePrice;
    }

    protected void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    protected void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }


    public abstract double getRentalPrice();

    protected abstract int getPaidRentalDays();


}