package MovieRental.Item;

import MovieRental.Transaction.Transaction;

public class ItemFactory {
    public static Movie getItem(String title, Movie.Category movieCategory) {
        return new Movie(title, movieCategory.ordinal());
    }

    public static Movie getItem(String title, int daysRented, Movie.Category movieCategory) {
        Movie movie = getItem(title, movieCategory);
        movie.setDaysRented(daysRented);
        return movie;
    }

    public static Movie getItem(String title, int daysRented, Movie.Category movieCategory, Transaction transaction) {
        Movie movie = getItem(title, daysRented, movieCategory);
        movie.setTransaction(transaction);
        return movie;
    }

    public static Game getItem(String title, Game.Category gameCategory) {
        return new Game(title, gameCategory.ordinal());
    }

    public static Game getItem(String title, int daysRented, Game.Category gameCategory) {
        Game game = getItem(title, gameCategory);
        game.setDaysRented(daysRented);

        return game;
    }

    public static Game getItem(String title, int daysRented, Game.Category gameCategory, Transaction transaction) {
        Game game = getItem(title, daysRented, gameCategory);
        game.setDaysRented(daysRented);
        game.setTransaction(transaction);
        return game;
    }
}
