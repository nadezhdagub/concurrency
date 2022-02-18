package ExacutorsSample;

import java.util.concurrent.*;

class MyTask1 implements Callable<String> {

    private final ExecutorService es;

    MyTask1(ExecutorService es) {
        this.es = es;
    }

    @Override
    public String call() throws Exception {
        Future<String> res = es.submit(new MyTask2());
        String res2 = res.get();
        return "Result" + res2;
    }
}

class MyTask2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "Result 2";
    }
}

public class ExecutorsErrors {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        Future<String> f = es.submit(new MyTask1(es));
        System.out.println("Result: " + f.get());
        es.shutdown();
    }
}
