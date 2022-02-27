package ThreadPoolExecutorEx;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //ThreadPoolExecutor es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
        ThreadPoolExecutor es = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
        es.submit(() -> {
            System.out.println("Task 1");
            Thread.sleep(5000);
            return null;
        });

        es.submit(() -> {
            System.out.println("Task 2");
            Thread.sleep(5000);
            return null;
        });

        es.submit(() -> {
            System.out.println("Task 3");
            Thread.sleep(5000);
            return null;
        });
    }
}
