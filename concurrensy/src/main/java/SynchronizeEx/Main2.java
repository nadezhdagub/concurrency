package SynchronizeEx;

import java.util.concurrent.*;

interface FactorialProducer {
    long calcFactorial(int val) throws InterruptedException;
}

class FactorialProducerImpl implements FactorialProducer {

    static class LastState {
        final int arg;
        final long res;

        LastState(int arg, long res) {
            this.arg = arg;
            this.res = res;
        }

        long getCachedValue(int val) {
            if (val == arg) {
                return res;
            } else return 0;
        }
    }

    int lastArg;
    long lastRes;

    @Override
    public synchronized long calcFactorial(int val) throws InterruptedException {
        System.out.println("Entering calcFactorial " + Thread.currentThread().getName());
        if(lastArg == val) {
            return lastRes;
        }
        lastArg = val;

        Thread.sleep(3000);
        long res = 1;
        for (int i = 1; i <= val; ++i) {
            res += i;
        }
        lastRes = res;
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
