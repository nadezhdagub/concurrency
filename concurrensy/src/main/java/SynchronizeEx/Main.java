package SynchronizeEx;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

interface Counter {
    void increment();
    int get();
}

class Task implements Callable<Void> {

    private final String name;

    Task(String name) {
        this.name = name;
    }

    @Override
    public Void call() throws Exception {
        System.out.println("Task " + name + " started in " + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("Task " + name + " ended");
        return null;
    }
}

class CounterImpl implements Counter {
    AtomicInteger count = new AtomicInteger();

    @Override
    public void increment() {
        count.incrementAndGet();
    }

    @Override
    public int get() {
        return count.get();
    }
}

public class Main {
    public static void main(String[] args) {
        Counter counter = new CounterImpl();
        countPrimers(1, 10000, counter);
        System.out.println("Prime nummbers count: " + counter.get());
    }

    private static void countPrimers(int from, int to, Counter counter) {
        for (int i = from; i<=to; ++i) {
            if(isPrime(i)) {
                counter.increment();
            }
        }
    }

    private static boolean isPrime(int val) {
        if(val == 1) return false;
        if (val == 2) return true;
        for (int i = 3; i <= val-1; ++i){
            if(val%i == 0) return false;
        }
        return true;
    }
}
