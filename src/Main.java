// Import biblioteki Arrays, która zawiera metody do operacji na tablicach
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;



// Definicja klasy BubbleSortAnalysis, która zawiera metody do testowania algorytmu sortowania bąbelkowego
 class BubbleSortAnalysis {

    // Metoda main, która jest punktem wejścia programu
    public static void main(String[] args) {
        // Definicja tablicy z przykładowymi danymi do sortowania
        int[] dane = new int[2000];
        for (int i = 0; i < dane.length; i++) {
            dane[i] = (int) (Math.random() * 2000);
        }
        System.out.println("");
        System.out.println("Hubert Hirt, 122711, Społeczna Akademia Nauk w Łodzi, Progam do badania czasu wykonania algorytmu na bazie tablicy danych");
        // Tworzenie kopii tablicy, aby porównać wyniki z Arrays.sort()
        int[] kopiaDanych = Arrays.copyOf(dane, dane.length); // Kopia do porównania z Arrays.sort()
        System.out.println("");
        System.out.println("");
        // Wyświetlenie tablicy przed sortowaniem
        System.out.println("Przed sortowaniem: " + Arrays.toString(dane));

        // Początek pomiaru czasu dla sortowania bąbelkowego
        long startCzas = System.nanoTime(); // Początek pomiaru czasu

        // Wywołanie metody bubbleSort, która sortuje tablicę
        int[] wynikBubble = bubbleSort(dane); // Wywołanie sortowania

        // Koniec pomiaru czasu dla sortowania bąbelkowego
        long czasBubble = System.nanoTime() - startCzas; // Obliczenie czasu wykonania

        // Początek pomiaru czasu dla sortowania optymalnego (Arrays.sort())
        startCzas = System.nanoTime(); // Początek pomiaru czasu

        // Wywołanie metody Arrays.sort(), która sortuje kopię tablicy
        Arrays.sort(kopiaDanych); // Sortowanie optymalne

        // Koniec pomiaru czasu dla sortowania optymalnego
        long czasOptimal = System.nanoTime() - startCzas; // Obliczenie czasu wykonania

        // Wyświetlenie wyników sortowania bąbelkowego
        System.out.println("\n=== Sortowanie bąbelkowe ===");
        System.out.println("Po sortowaniu: " + Arrays.toString(wynikBubble)); // Wyświetlenie posortowanej tablicy

        // Wyświetlenie czasu wykonania sortowania bąbelkowego
        System.out.println("Czas wykonania: " + czasBubble + " ns"); // Wyświetlenie czasu

        // Wyświetlenie teoretycznej złożoności czasowej sortowania bąbelkowego
        System.out.println("Złożoność teoretyczna: O(n^2)"); // Złożoność teoretyczna

        // Wyświetlenie wyników sortowania optymalnego
        System.out.println("\n=== Sortowanie optymalne (Arrays.sort()) ===");
        System.out.println("Po sortowaniu: " + Arrays.toString(kopiaDanych)); // Wyświetlenie posortowanej kopii

        // Wyświetlenie czasu wykonania sortowania optymalnego
        System.out.println("Czas wykonania: " + czasOptimal + " ns"); // Wyświetlenie czasu

        // Wyświetlenie teoretycznej złożoności czasowej sortowania optymalnego
        System.out.println("Złożoność teoretyczna: O(n log n)"); // Złożoność teoretyczna

        // Porównanie wyników i wyświetlenie wniosku
        System.out.println("\n[Wniosek] Sortowanie bąbelkowe jest " +
                (czasBubble > czasOptimal ? "wolniejsze" : "szybsze") +
                " niż Arrays.sort() w tym teście."); // Wnioski
    }

    // Metoda bubbleSort, która implementuje algorytm sortowania bąbelkowego
    public static int[] bubbleSort(int[] tablica) {
        // Pobranie długości tablicy
        int n = tablica.length; // Długość tablicy

        // Zmienna logiczna do sprawdzania, czy nastąpiła zamiana w iteracji
        boolean zamiana; // Flag zamiany

        // Liczniki operacji
        int porownania = 0; // Licznik porównań
        int zamiany = 0; // Licznik zamian

        // Główna pętla algorytmu bąbelkowego
        for (int i = 0; i < n - 1; i++) {
            // Ustawienie flagi zamiany na false na początku każdej iteracji
            zamiana = false; // Reset flagi

            // Wewnętrzna pętla porównująca sąsiadujące elementy
            for (int j = 0; j < n - i - 1; j++) {
                // Zwiększenie licznika porównań
                porownania++; // Inkrementacja licznika porównań

                // Sprawdzenie, czy elementy są w niewłaściwej kolejności
                if (tablica[j] > tablica[j + 1]) {
                    // Zamiana elementów
                    int temp = tablica[j]; // Tymczasowa zmienna do zamiany

                    // Przypisanie wartości elementu j+1 do elementu j
                    tablica[j] = tablica[j + 1];

                    // Przypisanie wartości z tymczasowej zmiennej do elementu j+1
                    tablica[j + 1] = temp;

                    // Ustawienie flagi zamiany na true
                    zamiana = true; // Ustawienie flagi zamiany

                    // Zwiększenie licznika zamian
                    zamiany++; // Inkrementacja licznika zamian
                }
            }

            // Jeśli nie nastąpiła żadna zamiana w iteracji, tablica jest już posortowana
            if (!zamiana) break; // Przerwanie pętli, jeśli brak zamian
        }

        // Wyświetlenie statystyk sortowania bąbelkowego
        System.out.println("\nStatystyki BubbleSort:");
        System.out.println("Liczba porównań: " + porownania); // Wyświetlenie liczby porównań

        // Wyświetlenie liczby zamian
        System.out.println("Liczba zamian: " + zamiany); // Wyświetlenie liczby zamian

        // Zwrócenie posortowanej tablicy
        return tablica; // Zwrócenie posortowanej tablicy
    }
}