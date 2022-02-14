package threads;

class MyTask implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Task {

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        Thread t = new MyThread(task);
       // t.setDaemon(true);
        t.start();
       // Thread.currentThread().sleep(2000);
        System.out.println("Main thread acquired exist");
    }
}
