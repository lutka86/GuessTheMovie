public class Main {
    public static void main(String [] args) throws Exception {

        //stworzenie konstruktora klasy Game
        RandomFilm film = new RandomFilm();
        //wywołanie metody klasy Game do wylosowania filmu
        film.randomFilm();

        Game newGame = new Game();
        newGame.userLetter();
        newGame.game();

       }
}

