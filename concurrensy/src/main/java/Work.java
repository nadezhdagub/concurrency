import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {

    private final Object res1;
    private final Object res2;

    Task(Object res1, Object res2) {
        this.res1 = res1;
        this.res2 = res2;
    }

    @Override
    public void run() {
        print(Thread.currentThread().getName() + "Taking lock 1");
        synchronized (res1) {
            print(Thread.currentThread().getName() + "Taking lock 2");
            synchronized (res2) {
                print(Thread.currentThread().getName() + "Took both locks");
            }
        }
        print(Thread.currentThread().getName() + "Done");
    }

    private void print(String text) {
        System.out.println(text);
    }
}

public class Work {
    public static void main(String[] args) {
        final Object res1 = new Object();
        final Object res2 = new Object();

        Task t1 = new Task(res1, res2);
        Task t2 = new Task(res2, res1);

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(t1);
        es.execute(t2);

        es.shutdown();
    }
}
