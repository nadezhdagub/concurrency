package VolatileEx;

import threads.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static volatile int count = 0;

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new Task1());
        es.execute(new Task2());

        es.shutdown();
    }

    static class Task1 implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (count >= 5) {
                    System.out.println("Counter is greater than 5 now");
                    break;
                }
            }
        }
    }

    static class Task2 implements Runnable {

        @Override
        public void run() {
            while (count < 10) {
                System.out.println("Counter is " + (count++) + " now");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}
