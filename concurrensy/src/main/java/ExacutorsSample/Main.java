package ExacutorsSample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MyT implements Callable<String> {

    private final String text;

    MyT(String text) {
        this.text = text;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Executing task...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception");
        }
        return " Hello " + text;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();

        MyT t1 = new MyT("Mary");
        MyT t2 = new MyT("Mary");
        MyT t3 = new MyT("Mary");

        List<MyT> tasks = new ArrayList<MyT>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        List<Future<String>> futures = es.invokeAll(tasks);

        for (Future<String> f: futures) {
            System.out.println("Task result =" + f.get());
        }
        es.shutdown();
    }
}
