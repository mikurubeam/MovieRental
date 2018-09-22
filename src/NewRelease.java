public class NewRelease extends Movie {
    public NewRelease(String title, int daysRented) {
        super(title, Category.NEW_RELEASE.ordinal());
        this.daysRented = daysRented;
        this.pricePerDay = 3;
    }

    @Override
    public int getBonusPoints() {
        if (this.daysRented > 1) {
            return this.bonusPoints++;
        }

        return this.bonusPoints;
    }
}
