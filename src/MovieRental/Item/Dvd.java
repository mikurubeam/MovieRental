package MovieRental.Item;

import MovieRental.Util.StringUtil;

public class Dvd extends Movie {
    public Dvd(String title, int movieType) {
        super(title, movieType);
    }

//    private Movie.Category getMovieType() {
//        return ((Movie)this.item).getMovieType();
//    }

    @Override
    public String getTitle() {
        return super.getTitle() + " (DVD)";
    }

//    @Override
//    public String toString() {
//        return StringUtil.getTableRow(
//                1,
//                1,
//                this.getTitle(),
//                this.getMovieType().toString(),
//                String.valueOf(this.getTransaction().getDaysRented()),
//                String.format(StringUtil.USD, this.getRentalPrice()),
//                String.valueOf(this.getFrequentRentalPoints())
//        );
//    }
}
