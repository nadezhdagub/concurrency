package SynchronizeEx;

import java.util.concurrent.*;

interface FactorialProducer {
    long calcFactorial(int val);
}

class FactorialProducerImpl implements FactorialProducer {

    @Override
    public long calcFactorial(int val) {
        long res = 1;
        for (int i = 1; i < val; ++i) {
            res += i;
        }
        return res;
    }
}

public class Main2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FactorialProducer fp = new FactorialProducerImpl();
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Long> f1 = es.submit(() -> fp.calcFactorial(10));
        Future<Long> f2 =es.submit(() -> fp.calcFactorial(10));

        System.out.println(f1.get());
        System.out.println(f2.get());
    }
}
