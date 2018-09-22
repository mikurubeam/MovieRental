public class Game extends Rental {
    public double getRentalPrice() {
        return 0;
    }

    protected int getPaidRentalDays() {
        return 0;
    }

    public String getTableHeader() {
        return "Game Rental Summary:\n" +
            StringUtil.getTableRow(
                1,
                2,
                "TITLE",
                "SYSTEM",
                "DAYS RENTED",
                "PRICE"
            );
    }

    @Override
    public String toString() {
        return StringUtil.getTableRow(
                1,
                1,
                "Some Game",
                "Some System",
                String.valueOf(this.daysRented),
                String.format(StringUtil.USD, this.getRentalPrice())
        );
    }
}
