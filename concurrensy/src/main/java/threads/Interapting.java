package threads;

class MyTaskWithInteraption implements Runnable {

    int i = 0;

    @Override
    public void run() {
        //while (!Thread.currentThread().isInterrupted()) {
        while (!Thread.interrupted()) {
            System.out.println("i=" + (++i));
        }
        System.out.println("Thread existed");
    }
}

public class Interapting {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyTaskWithInteraption());
        t.start();
        Thread.sleep(2000);
        t.interrupt();
        System.out.println("Main thread acquired exist");
    }
}
