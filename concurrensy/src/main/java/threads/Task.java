package threads;

class MyTask implements Runnable {

    int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                System.out.println("i=" + (++i));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            cleanup();
        }

//        for (int i = 0; i < 100; i++) {
//            if (Thread.interrupted()) {
//                System.out.println("Thread is interrupted");
//                break;
//            }
//            System.out.println("Thread " + i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
    void cleanup() {
        System.out.println("Cleaning up");
    }
}

public class Task {

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        Thread t = new MyThread(task);
       // t.setDaemon(true);
        t.start();
        Thread.sleep(2000);
        t.interrupt();
       // t.stop();
       // Thread.currentThread().sleep(2000);
        System.out.println("Main thread acquired exist");
    }
}
