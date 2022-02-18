package ExacutorsSample;

import java.util.concurrent.*;

class MyTask implements Callable<String> {

    private final String text;

    MyTask(String text) {
        this.text = text;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Executing task");
        Thread.sleep(2000);
        return text;
    }
}

class MyThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println("newThread called ");
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}

public class ThreadFactoryCl {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadFactory threadFactory = new MyThreadFactory();
        ExecutorService es = Executors.newFixedThreadPool(4, threadFactory);
        Future<String> future = es.submit(new MyTask("Hello"));
        System.out.println("Result= " + future.get());
        future = es.submit(new MyTask("Goodbye"));
        System.out.println("Result= " + future.get());

        //es.shutdown();
    }
}
