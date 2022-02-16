package threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class TaskFlag implements Runnable {

    private final BlockingQueue<Integer> que;
    boolean isCancelled;
    int i = 0;

    TaskFlag(BlockingQueue<Integer> que) {
        this.que = que;
    }

    @Override
    public void run() {
        while (!isCancelled) {
            ++i;
            try {
                System.out.println("Putting " + i);
                que.put(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread exited");
    }
}

public class CurremtFlag {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> que = new ArrayBlockingQueue<>(1000);
        TaskFlag task = new TaskFlag(que);
        Thread t = new Thread(task);
        t.start();
        Thread.sleep(2000);
        task.isCancelled = true;
        System.out.println("Main thread exited");
    }
}
