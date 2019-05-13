import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class RandomFilm {

    static String chosenFilm;
    static String guess;

    public void randomFilm() throws Exception {

        //tworzenie obiektu typu File do wczytania pliku tekstowego
        File file = new File("films.txt");

        //stworzenie tablicy, do której zostaną wczytane filmy z pliku tekstowego
        String[] filmList = new String[25];
        Scanner fileScanner = new Scanner(file);

        //wczytanie Stringów z pliku tekstowego do tablicy
        while (fileScanner.hasNextLine()) {
            for (int i = 0; i < 25; i++) {
                filmList[i] = fileScanner.nextLine();
            }
        }

        Random r = new Random();
        //wylosowanie liczby całkowitej z przedziału 0-24
        int randomNumber = r.nextInt(25);

        //zmienna do której zostanie wczytany wybrany film, na podstawie losowo wybranego indeksu z tablicy
        chosenFilm = filmList[randomNumber];

        //variable to create movie title with "-" instead of letters (zmienna, do której wczytane zostaną znaki "-" zamiast liter)
        guess = "";
        //zmienna, do której zostanie wczytany po kolei każdy znak z danego Stringa (nazwy filmu)
        char singleSign;
        char space = ' ';
        //licznik liter w haśle
        int count = 0;

        //przejście przez wszystkie pojedyncze znaki w Stringu (nazwie filmu)
        for (int i = 0; i < filmList[randomNumber].length(); i++) {
            singleSign = chosenFilm.charAt(i);
            //porównanie czy wybrany znak to spacja czy nie (jeśli tak, to pozostaje spacją, jeśli nie to litera zamieniana jest na "_"
            if (singleSign != space) {
                guess += "-";
                count++;
            } else {
                guess += " ";
            }
        }

        System.out.println("Guess the movie. It has got " + count + " letters.");
        System.out.println(guess);
    }
}