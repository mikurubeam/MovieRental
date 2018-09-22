import java.util.ArrayList;
import java.util.List;

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

    public abstract String getTableHeader();

    public static <T> List<Rental> getFilteredList(List<Rental> rentals, Class<T> objectType) {
        List<Rental> list = new ArrayList<>();

        for (Rental rental : rentals) {
            if (objectType.isInstance(rental)) {
                list.add(rental);
            }
        }

        return list;
    }
}
