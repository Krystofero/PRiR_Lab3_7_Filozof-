package Lab3_7_Filozof;

import java.util.Random;
import java.util.concurrent.Semaphore ;
public class Filozof3 extends Thread {
    static int MAX;
    static Semaphore [] widelec;
    int mojNum;
    Random losuj ;
    public Filozof3 ( int nr , int MAX) {
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
            int strona = this.losuj.nextInt( 2 ) ;
            boolean podnioslDwaWidelce = false ;
            do {
                if (strona == 0) {
                    this.widelec[mojNum].acquireUninterruptibly ();
                    if(!( widelec[(mojNum +1 ) % this.MAX].tryAcquire( ))) {
                        this.widelec[mojNum].release();
                    } else {
                        podnioslDwaWidelce = true ;
                    }
                } else {
                    this.widelec[(mojNum + 1) % this.MAX].acquireUninterruptibly();
                    if (!(this.widelec[mojNum].tryAcquire())) {
                        this.widelec[(mojNum + 1) % this.MAX].release();
                    } else {
                        podnioslDwaWidelce = true;
                    }
                }
            } while ( podnioslDwaWidelce == false);
            System.out.println ( "Zaczyna jesc nr:" + mojNum) ;
            try {
                Thread.sleep((long)(3000 * Math.random()));
            } catch (InterruptedException e) {
            }
            System.out.println("Konczy jesc nr:" + mojNum) ;
            this.widelec[mojNum].release();
            this.widelec[(mojNum + 1) % this.MAX].release();
        }
    }
}
