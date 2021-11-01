package Lab3_7_Filozof;

import java.util.concurrent.Semaphore ;
public class Filozof2 extends Thread {
    static int MAX;
    static Semaphore [] widelec;
    int mojNum;
    public Filozof2 ( int nr , int MAX) {
        mojNum=nr;
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
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            if (mojNum == 0) {
                widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
                widelec [mojNum].acquireUninterruptibly ( ) ;
            } else {
                widelec [mojNum].acquireUninterruptibly ( ) ;
                widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
            }
            // jedzenie
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            try {
                Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+mojNum) ;
            widelec [mojNum].release ( ) ;
            widelec [ (mojNum+1)%MAX].release ( ) ;
        }
    }
}
