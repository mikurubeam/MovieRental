package MovieRental.Item;

import MovieRental.Transaction.Transaction;

import java.time.LocalDate;

public class ItemFactory {
    public static Movie getItem(String title, Movie.Category movieCategory) {
        return new Movie(title, movieCategory.ordinal());
    }

    public static Movie getItem(String title, Movie.Category movieCategory, LocalDate releaseDate) {
        Movie movie = getItem(title, movieCategory);
        movie.setReleaseDate(releaseDate);
        return movie;
    }

    public static Movie getItem(
            String title,
            Movie.Category movieCategory,
            LocalDate releaseDate,
            Transaction transaction
    ) {
        Movie movie = getItem(title, movieCategory, releaseDate);
        movie.setTransaction(transaction);
        return movie;
    }

    public static Dvd getItem(Movie movie) {
        Dvd dvd = new Dvd(movie.getTitle(), movie.getMovieType().ordinal());
        dvd.setReleaseDate(movie.getReleaseDate());
        return dvd;
    }

    public static Dvd getItem(Movie movie, Transaction transaction) {
        Dvd dvd =  getItem(movie);
        dvd.setTransaction(transaction);
        return dvd;
    }

    public static Game getItem(String title, Game.Category gameCategory) {
        return new Game(title, gameCategory.ordinal());
    }

    public static Game getItem(String title, Game.Category gameCategory, LocalDate releaseDate) {
        Game game = getItem(title, gameCategory);
        game.setReleaseDate(releaseDate);
        return game;
    }

    public static Game getItem(
            String title,
            Game.Category gameCategory,
            LocalDate releaseDate,
            Transaction transaction
    ) {
        Game game = getItem(title, gameCategory, releaseDate);
        game.setTransaction(transaction);
        return game;
    }

    public static CompactDisc getItem(String title, CompactDisc.Category cdCategory) {
        return new CompactDisc(title, cdCategory.ordinal());
    }

    public static CompactDisc getItem(String title, CompactDisc.Category cdCategory, LocalDate releaseDate) {
        CompactDisc cd = getItem(title, cdCategory);
        cd.setReleaseDate(releaseDate);
        return cd;
    }

    public static CompactDisc getItem(
            String title,
            CompactDisc.Category cdCategory,
            LocalDate releaseDate,
            Transaction transaction
    ) {
        CompactDisc cd = getItem(title, cdCategory, releaseDate);
        cd.setTransaction(transaction);
        return cd;
    }

    public static Book getItem(String title, Book.Category bookCategory) {
        return new Book(title, bookCategory.ordinal());
    }

    public static Book getItem(String title, Book.Category bookCategory, LocalDate releaseDate) {
        Book book = getItem(title, bookCategory);
        book.setReleaseDate(releaseDate);
        return book;
    }

    public static Book getItem(
            String title,
            Book.Category bookCategory,
            LocalDate releaseDate,
            Transaction transaction
    ) {
        Book book = getItem(title, bookCategory, releaseDate);
        book.setTransaction(transaction);
        return book;
    }
}
