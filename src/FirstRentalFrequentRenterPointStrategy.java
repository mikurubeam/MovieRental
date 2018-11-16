public class FirstRentalFrequentRenterPointStrategy extends StandardFrequentRenterPointStrategy{
    public FirstRentalFrequentRenterPointStrategy(Rental rental) {
        super(rental);
    }

    public int getFrequentRentalPoints() {
        Customer customer = this.rental.getCustomer();

        if (customer.hasMultipleMovieCategories(2)
                || (customer.isInAgeRange(18, 22) && customer.hasNewReleases())
        ) {
            return this.bonusPoints * 2;
        }

        return this.bonusPoints;
    }
}
