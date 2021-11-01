Programowanie równolegle i rozproszone - projekt 7 labolatorium 3 Problem Filozofów

Program jest pozwala na wybór z 3 programów dotyczących problemu filozofów z wykładu. 

W klasie Filozof , funkcji main program pyta o liczbę filozofów między 2 a 100 oraz o metodę którą ma wykonać. Następnie tworzy podaną liczbę obiektów dla danej metody i uruchamia ich pracę na wielu wątkach:

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
    
Dla każdego z wariantów rozwiązania problemu filozofów konstruktor przypisuje odpowidnie wartości:

    public Filozof1 ( int nr , int MAX) {
        this.mojNum=nr;
        this.MAX = MAX;
        this.widelec = new Semaphore[MAX];
        for ( int i = 0; i < this.MAX; i++) {
            this.widelec[i] = new Semaphore( 1 );
        }
    }

,a metoda run wywświelta odpiwednie komunikaty o myśleniu , jedzeniu , braniu lub odkładaniu widelca a także usypia na określony czas filozofa na podstawie podanej na wykładzie metody:

    public void run ( ) {
        while ( true ) {
            // myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (7000* Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            widelec [mojNum].acquireUninterruptibly ( ) ;//przechwycenie L widelca
            widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;//przechwycenie P widelca
            // jedzenie
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000* Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+mojNum) ;
            widelec [mojNum].release ( ) ;//zwolnienie L widelca
            widelec [ (mojNum+1)%MAX].release ( ) ;//zwolnienie P widelca
        }
    }
