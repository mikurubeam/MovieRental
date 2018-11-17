public class RentalFactory {
    public static Movie getRental(String title, Movie.Category movieCategory) {
        return new Movie(title, movieCategory.ordinal());
    }

    public static Movie getRental(String title, int daysRented, Movie.Category movieCategory) {
        Movie movie = getRental(title, movieCategory);
        movie.setDaysRented(daysRented);

        return movie;
    }

    public static Movie getRental(String title, int daysRented, Movie.Category movieCategory, Customer customer) {
        Movie movie = getRental(title, daysRented, movieCategory);
        movie.setCustomer(customer);
        customer.addRental(movie);

        return movie;
    }

//    public static Movie getFreeRental(String title, int daysRented, Movie.Category movieCategory, Customer customer) {
//        Movie movie = getRental(title, daysRented, movieCategory, customer);
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

    public static Game getRental(String title, int daysRented, Game.Category gameCategory, Customer customer) {
        Game game = getRental(title, daysRented, gameCategory);
        game.setDaysRented(daysRented);
        game.setCustomer(customer);
        customer.addRental(game);
        return game;
    }

//    public static Game getFreeRental(String title, int daysRented, Game.Category gameCategory, Customer customer) {
//        Game game = getRental(title, daysRented, gameCategory, customer);
//        game.setFreeRental(true);
//
//        return game;
//    }
}
