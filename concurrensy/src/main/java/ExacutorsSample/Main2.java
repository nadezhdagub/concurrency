package ExacutorsSample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Spaceship implements Runnable {

    private final String name;

    Spaceship(String name)  {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Spaceship " + name + " launched");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Spaceship " + name + " landed");
    }
}

public class Main2 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        ExecutorService ess = Executors.newScheduledThreadPool(1);

        es.execute(new Spaceship("Apollo"));
        es.execute(new Spaceship("Nostromo"));
        es.execute(new Spaceship("Soyuz"));
        es.execute(new Spaceship("Vostok"));
    }
}
