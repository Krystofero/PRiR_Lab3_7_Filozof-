package Lab3_7_Filozof;

import java.util.concurrent.Semaphore ;
public class Filozof1 extends Thread {
    static int MAX;
    static Semaphore [] widelec;
    int mojNum;
    public Filozof1 ( int nr , int MAX) {
        this.mojNum=nr;
        this.MAX = MAX;
        this.widelec = new Semaphore[MAX];
        for ( int i = 0; i < this.MAX; i++) {
            this.widelec[i] = new Semaphore( 1 );
        }
    }
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
}

