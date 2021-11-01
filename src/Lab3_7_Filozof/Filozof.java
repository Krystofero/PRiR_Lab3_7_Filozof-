package Lab3_7_Filozof;

import java.util.Scanner;
// Wszystkie 3 wersje filozofów
public class Filozof {
    public static void main ( String [] args ) {

            Scanner sc = new Scanner(System.in);
            int liczba_filozofow = 0;

            while(liczba_filozofow < 2 || liczba_filozofow > 100) {
                System.out.println("Podaj liczbe filozofów(min 2 , max 100): ");
                liczba_filozofow = sc.nextInt();
            }

            System.out.println("Wybierz wariant rozwiązania (1.Pierwszy sposób 2.Drugi sposób 3.Trzeci sposób)");
            int wybor = sc.nextInt();

            switch(wybor) {
                case 1: {
                    for ( int i = 0; i < liczba_filozofow; i++)
                        new Filozof1(i,liczba_filozofow).start();
                } break;
                case 2: {
                    for ( int i = 0; i < liczba_filozofow; i++)
                        new Filozof2(i, liczba_filozofow).start();
                } break;
                case 3: {
                    for ( int i = 0; i < liczba_filozofow; i++)
                        new Filozof3(i, liczba_filozofow).start();
                } break;
            }
    }
}
