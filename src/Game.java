import java.util.Scanner;

public class Game {

    //letter chosen by user (litera wybrana przez użytkownika)
    private char letter;
    //number of loosing chances to find out the movie title (zmienna do naliczania punktów za odgadnięcie błędnej litery (10 błędnie odgadniętych liter - przegrana)
    private int points = 0;
    //wrong letters guessing by user (tablica do przechowywania błędnie odgadniętych liter)
    private char[] wrongLetters = new char[10];
    //number of index of occurance the letter(zmienna, do której zostaje zapisana wartość indeksu pierwszego wystąpienia w danym Stringu litery wybranej przez użytkownika)
    private int index;
    //number of occurances one letter chosen by user(licznik wystąpień danej litery)
    private int count;
    //indexes with occurances (in movie title) one letter chosen by user (tablica do zapisywania indeksów, w których występuje litera usera (długość tablicy określona na połowę długości nazwy filmu)
    int[] occurences;
    //stworzenie obiektu StringBuilder, żeby można było podmienić w Stringu "char" (w metodzie replace możliwa jest tylko podmiana "String")
    //actualResult to zmienna, w której na bieżąco będziemy podmieniać "-" na zgadnięte przez usera litery
    StringBuilder actualResult = new StringBuilder(RandomFilm.guess);

    //metoda do wpisania litery wybranej przez użytkownika
    public char userLetter() {
        //prośba do użytkownika o wpisanie litery
        System.out.print("Guess the letter: ");
        //stworzenie obiektu Scanner i  zainicjowanie zmiennej, do której zostanie zapisana litera wybrana przez użytkownika
        Scanner guessLetter = new Scanner(System.in);
        letter = guessLetter.next().charAt(0);
        return letter;
    }

    //metoda dodająca do tablicy litery błędnie odgadnięte przez usera i wyświetlająca ile i jakie litery błędnie podał
    public void wrongLetter() {
        System.out.print("You have guessed (" + points + ") wrong letters: ");
        for (int j = 0; j < points; j++) {
            System.out.print(wrongLetters[j] + " ");
        }
        System.out.println();
    }


    public void game() {
        //zainicjowanie zmiennej indeksem z pierwszym wystąpieniem litery wybranej przez użytkownika
        index = RandomFilm.chosenFilm.indexOf(letter);
        count = 0;
        //zainicjowanie i określenie wielkości tablicy do przechowywania indeksów z wystąpieniem tej samej litery w haśle
        //długość = połowa długości hasła
        occurences = new int[RandomFilm.chosenFilm.length() / 2];
        //pętla do zapełnienia tablicy indeksami wystąpień litery usera
        while (index >= 0) {
            //pod indeksem 0 zapisujemy do tablicy wartość indeksu pierwszego wystąpienia litery
            occurences[count] += index;
            count++;
            //zmieniamy wartość zmiennej indeks, zwiększając wartość indeksu wystąpienia litery usera o 1 (np. litera wystąpieła pod indeksem nr 3,
            // to kolejne wyszukanie zaczyna się od indelsu nr 4
            index = RandomFilm.chosenFilm.indexOf(letter, index + 1);
        }

        //pętla do zamiany "-" na litery zgadnięte przez usera (pobrane z tablicy z wcześniejszej pętli)
        for (int i = 0; i < count; i++) {
            actualResult.setCharAt(occurences[i], letter);
                //jeśli aktualnie odgadnięte litery dopełniają całe hasło to wygrana i koniec programu
                if ((actualResult.toString()).equals(RandomFilm.chosenFilm)) {
                System.out.println("You Win! You guessed: " + RandomFilm.chosenFilm);
                System.exit(0);
            }
        }

        //jeśli nie ma w haśle litery podanej przez usera to informacja dla usera i zwiększenie o 1 straconych szans
        if(count == 0) {
                System.out.println("There is no " + "'" + letter + "'" + " in the title.");
                wrongLetters[points] += letter;
                points++;
            }

        //jeśli stracone szanse osiągną max (czyli 10) to przegrana i koniec programu
        //w przeciwnym wypadku ponowne wpisanie litery i rozstrzygnięcie czy jest w haśle czy nie
        if (points == 10) {
                    System.out.println("You missed 10 chances and You loose! The right answer is: " + RandomFilm.chosenFilm);
                } else {
                    System.out.println(actualResult);
                    wrongLetter();
                    userLetter();
                    game();
                }
    }
}



