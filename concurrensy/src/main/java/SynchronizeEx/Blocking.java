package SynchronizeEx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyBlockingQue {
    private final int SIZE = 10;
    private int[] vals = new int[SIZE];
    private int indexPut;
    private int indexGet;
    private int count;
    
    public synchronized void put(int val) throws InterruptedException {
        while(isFull()) {
        wait();    
        }
        vals[indexPut] = val;
        if(++indexPut == SIZE) {
            indexPut = 0;
        }
        count++;
        notifyAll();
    }

    private boolean isFull() {
        return count == SIZE;
    }

    public synchronized int get() throws InterruptedException {
        while(isEmpty()) {
            wait();
        }
        int val = vals[indexGet];
        if(++indexGet == SIZE) {
            indexGet = 0;
        }
        count--;
        notifyAll();
        return val;
    }

    private boolean isEmpty() {
        return count == 0;
    }
}

class Producer implements Runnable {
    private MyBlockingQue que;

    Producer(MyBlockingQue que) {
        this.que = que;
    }

    @Override
    public void run() {
        int val = 0;
        while(!Thread.interrupted()) {
            try {
                ++val;
                que.put(val);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            System.out.println("Put " + val);
        }
    }
}

class Consumer implements Runnable{
    private MyBlockingQue que;

    Consumer(MyBlockingQue que) {
        this.que = que;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                int val = que.get();
                System.out.println("Get " + val);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}

public class Blocking {
    public static void main(String[] args) {
        MyBlockingQue que = new MyBlockingQue();
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(new Producer(que));
        es.submit(new Consumer(que));
        
    }
}
