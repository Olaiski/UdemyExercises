package no.olai;

public class Main {

    public static void main(String[] args) {

        Countdown countdown = new Countdown();


        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");

        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

class Countdown { // Teller ned fra 10 til 1, forandrer farge på navnet til thread name

    // Begge trådene delere samme variabel (heap) hvis man bruker samme objekt i main...
    // Hvis vi bruker en lokal variabel i for-loop'en, har begge trådene en egen variabel i stacken
    private int i;


    public void doCountdown() { // Sync -> Want this whole method to run before another can run it..
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_BLUE;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_RED;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;

        }

        synchronized (this) { // Må bruke en "delt" variabel, ikke lokal variabel!
            for (i=10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i );
            }
        }
    }
}

class CountdownThread extends Thread { // start() i main -> bruker run()

    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }

}