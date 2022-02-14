package threads;

class MyThread extends Thread {

    private final Object lock;

    MyThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
//        System.out.println("Entering run. Thread state= " + getState());
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Thread terminates");
        System.out.println("My thread acquiring lock");
        synchronized (lock) {
            System.out.println("My thread acquired lock");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("My thread released lock");
    }
}

public class Main2 {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        MyThread task = new MyThread(lock);
        Thread t = new MyThread(task);
        t.start();
        Thread.currentThread().sleep(500);
        System.out.println("My thread tries acquiring lock");
        synchronized (lock) {
            System.out.println("Main thread acquired lock");
        }
        System.out.println("Main thread acquired exist");

//        Thread t = new MyThread();
//        //System.out.println("Thread state= " + t.getState());
//        t.start();
//        t.join();
//        // Thread.currentThread().sleep(4000);
//        System.out.println("Thread state= " + t.getState());
    }
}
