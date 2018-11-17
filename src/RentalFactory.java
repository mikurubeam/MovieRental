public class RentalFactory {
    public static Movie getRental(String title, Movie.Category movieCategory) {
        return new Movie(title, movieCategory.ordinal());
    }

    public static Movie getRental(String title, int daysRented, Movie.Category movieCategory) {
        Movie movie = getRental(title, movieCategory);
        movie.setDaysRented(daysRented);

        return movie;
    }

    public static Movie getRental(String title, int daysRented, Movie.Category movieCategory, Transaction transaction) {
        Movie movie = getRental(title, daysRented, movieCategory);
        movie.setTransaction(transaction);
//        transaction.addRental(movie);

        return movie;
    }

//    public static Movie getFreeRental(String title, int daysRented, Movie.Category movieCategory, Transaction transaction) {
//        Movie movie = getItem(title, daysRented, movieCategory, transaction);
//        movie.setFreeRental(true);
//
//        return movie;
//    }

    public static Game getRental(String title, Game.Category gameCategory) {
        return new Game(title, gameCategory.ordinal());
    }

    public static Game getRental(String title, int daysRented, Game.Category gameCategory) {
        Game game = getRental(title, gameCategory);
        game.setDaysRented(daysRented);

        return game;
    }

    public static Game getRental(String title, int daysRented, Game.Category gameCategory, Transaction transaction) {
        Game game = getRental(title, daysRented, gameCategory);
        game.setDaysRented(daysRented);
        game.setTransaction(transaction);
//        transaction.addRental(game);
        return game;
    }

//    public static Game getFreeRental(String title, int daysRented, Game.Category gameCategory, Transaction transaction) {
//        Game game = getItem(title, daysRented, gameCategory, transaction);
//        game.setFreeRental(true);
//
//        return game;
//    }
}
