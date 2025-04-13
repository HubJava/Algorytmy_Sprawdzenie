// Importujemy bibliotekę Arrays, która zawiera metody do pracy z tablicami
import java.util.Arrays;

// Definicja klasy Hubert_Hirt_sprawdzenie_algorytmu, zawierającej implementację algorytmu sortowania bąbelkowego oraz jego porównanie z Arrays.sort()
class Hubert_Hirt_sprawdzenie_algorytmu {

    // Metoda pomocnicza do pomiaru zajętej pamięci w bajtach
    public static long getUsedMemory() {
        // Pobranie obiektu Runtime, który zarządza pamięcią JVM
        Runtime runtime = Runtime.getRuntime();
        // Obliczenie używanej pamięci: całkowita pamięć - wolna pamięć
        return runtime.totalMemory() - runtime.freeMemory();
    }

    // Metoda main, będąca punktem wejścia programu
    public static void main(String[] args) {
        // Definicja tablicy z 600 losowymi liczbami całkowitymi
        int[] dane = new int[2500];
        for (int i = 0; i < dane.length; i++) {
            dane[i] = (int) (Math.random() * 2500); // Wypełnienie tablicy losowymi wartościam
        }

        // Wyświetlenie informacji o autorze oraz celu programu
        System.out.println("");
        System.out.println("Hubert Hirt, 122711, Społeczna Akademia Nauk w Łodzi, Program do badania czasu wykonania algorytmu na bazie tablicy danych");

        // Tworzenie kopii tablicy na potrzeby porównania wyników z Arrays.sort()
        int[] kopiaDanych = Arrays.copyOf(dane, dane.length);

        // Wyświetlenie zawartości tablicy przed sortowaniem
        System.out.println("");
        System.out.println("");
        System.out.println("Przed sortowaniem: " + Arrays.toString(dane));

        // Pomiar pamięci przed sortowaniem bąbelkowym
        System.gc(); // Sugerowanie garbage collectorowi zwolnienia zasobów
        long pamiecPrzedBubble = getUsedMemory(); // Pobranie ilości zajętej pamięci przed sortowaniem bąbelkowym

        // Pomiar czasu wykonania sortowania bąbelkowego
        long startCzas = System.nanoTime(); // Pobranie czasu rozpoczęcia sortowania w nanosekundach
        int[] wynikBubble = bubbleSort(dane); // Wywołanie metody sortowania bąbelkowego
        long czasBubble = System.nanoTime() - startCzas; // Obliczenie czasu wykonania sortowania bąbelkowego w nanosekundach

        // Pomiar pamięci po sortowaniu bąbelkowym
        long pamiecPoBubble = getUsedMemory(); // Pobranie ilości zajętej pamięci po sortowaniu bąbelkowym

        // Pomiar pamięci przed sortowaniem optymalnym (Arrays.sort())
        System.gc(); // Ponowne sugerowanie GC zwolnienia pamięci
        long pamiecPrzedOptimal = getUsedMemory(); // Pobranie ilości zajętej pamięci przed sortowaniem optymalnym
        startCzas = System.nanoTime(); // Pobranie czasu rozpoczęcia sortowania optymalnego w nanosekundach
        Arrays.sort(kopiaDanych); // Wywołanie wbudowanego algorytmu sortowania (Arrays.sort())
        long czasOptimal = System.nanoTime() - startCzas; // Obliczenie czasu wykonania sortowania optymalnego w nanosekundach

        // Pomiar pamięci po sortowaniu optymalnym (Arrays.sort())
        long pamiecPoOptimal = getUsedMemory(); // Pobranie ilości zajętej pamięci po sortowaniu optymalnym

        // Wyświetlenie wyników dla sortowania bąbelkowego
        System.out.println("\n=== Sortowanie bąbelkowe ===");
        System.out.println("Po sortowaniu: " + Arrays.toString(wynikBubble)); // Wyświetlenie posortowanej tablicy
        System.out.println("Czas wykonania: " + czasBubble + " ns"); // Wyświetlenie czasu wykonania sortowania
        System.out.println("Złożoność teoretyczna: O(n^2)"); // Wyświetlenie złożoności algorytmu
        System.out.println("Pamięć przed sortowaniem: " + pamiecPrzedBubble + " bajtów"); // Wyświetlenie pamięci przed sortowaniem bąbelkowym
        System.out.println("Pamięć po sortowaniu: " + pamiecPoBubble + " bajtów"); // Wyświetlenie pamięci po sortowaniu bąbelkowym
        System.out.println("Różnica pamięci: " + (pamiecPoBubble - pamiecPrzedBubble) + " bajtów"); // Wyświetlenie różnicy w pamięci

        // Wyświetlenie wyników dla sortowania optymalnego
        System.out.println("\n=== Sortowanie optymalne (Arrays.sort()) ===");
        System.out.println("Po sortowaniu: " + Arrays.toString(kopiaDanych)); // Wyświetlenie posortowanej kopii tablicy
        System.out.println("Czas wykonania: " + czasOptimal + " ns"); // Wyświetlenie czasu wykonania sortowania
        System.out.println("Złożoność teoretyczna: O(n log n)"); // Wyświetlenie złożoności algorytmu
        System.out.println("Pamięć przed sortowaniem: " + pamiecPrzedOptimal + " bajtów"); // Wyświetlenie pamięci przed sortowaniem optymalnym
        System.out.println("Pamięć po sortowaniu: " + pamiecPoOptimal + " bajtów"); // Wyświetlenie pamięci po sortowaniu optymalnym
        System.out.println("Różnica pamięci: " + (pamiecPoOptimal - pamiecPrzedOptimal) + " bajtów"); // Wyświetlenie różnicy w pamięci

        // Wyświetlenie wniosku porównania czasów
        System.out.println("\n[Wniosek] Sortowanie bąbelkowe jest " +
                (czasBubble > czasOptimal ? "wolniejsze" : "szybsze") +
                " niż Arrays.sort() w tym teście.");
        System.out.println();
        System.out.println("WYKORZYSTANE FUNCKJE (n^2) oraz (n log n)");
    }

    // Implementacja algorytmu sortowania bąbelkowego
    public static int[] bubbleSort(int[] tablica) {
        int n = tablica.length; // Pobranie długości tablicy
        boolean zamiana; // Flaga służąca do monitorowania, czy nastąpiła zamiana elementów
        int porownania = 0; // Licznik porównań w algorytmie
        int zamiany = 0; // Licznik zamian w algorytmie

        // Główna pętla przechodząca przez tablicę
        for (int i = 0; i < n - 1; i++) {
            zamiana = false; // Resetowanie flagi zamiany
            for (int j = 0; j < n - i - 1; j++) {
                porownania++; // Inkrementacja licznika porównań
                if (tablica[j] > tablica[j + 1]) { // Sprawdzenie, czy elementy są w niewłaściwej kolejności
                    // Zamiana elementów
                    int temp = tablica[j];
                    tablica[j] = tablica[j + 1];
                    tablica[j + 1] = temp;
                    zamiana = true; // Ustawienie flagi zamiany
                    zamiany++; // Inkrementacja licznika zamian
                }
            }
            if (!zamiana) break; // Przerwanie pętli, jeśli tablica jest już posortowana
        }

        // Wyświetlenie statystyk sortowania bąbelkowego
        System.out.println("\nStatystyki BubbleSort:");
        System.out.println("Liczba porównań: " + porownania);
        System.out.println("Liczba zamian: " + zamiany);
        return tablica; // Zwrócenie posortowanej tablicy

    }

}
