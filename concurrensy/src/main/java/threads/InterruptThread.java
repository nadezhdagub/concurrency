package threads;

class MyT implements Runnable{

    int i = 0;

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            System.out.println("i=" + (++i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interruption Exception caught");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread existed");
    }
}

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyT());
        t.start();
        Thread.sleep(3000);
        t.interrupt();
        System.out.println("Main thread exist");
    }
}
