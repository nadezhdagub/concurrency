package PhaserEx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class Project {
    Phaser phaser = new Phaser(1);
    ExecutorService es = Executors.newCachedThreadPool();

    public void start() {

        es.submit(new Programmer("Architect ", this));
        phaser.arriveAndAwaitAdvance();
        es.submit(new Programmer("Architect ", this));
        es.submit(new Programmer("Senior developer ", this));
        phaser.arriveAndAwaitAdvance();
        es.submit(new Programmer("Junior developer ", this));
        es.submit(new Programmer("Tester ", this));
        phaser.arriveAndAwaitAdvance();
        es.submit(new Programmer("Tester ", this));
        phaser.arriveAndAwaitAdvance();

        es.shutdown();
    }

    public static void main(String[] args) {
        Project p = new Project();
        p.start();
    }
}
