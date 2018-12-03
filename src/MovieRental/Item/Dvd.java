package MovieRental.Item;

public class Dvd extends Movie {
    public Dvd(String title, int movieType) {
        super(title, movieType);
    }

    @Override
    public String getTitle() {
        return super.getTitle() + " (DVD)";
    }
}
