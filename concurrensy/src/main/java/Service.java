import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Services implements Runnable{

    private static ExecutorService es = Executors.newCachedThreadPool();
    private final String name;
    private final long delay;
    private final CountDownLatch latch;

    Services(String name, long delay, CountDownLatch latch) {
        this.name = name;
        this.delay = delay;
        this.latch = latch;
    }

    void init() {
        es.execute(this);
    }

    @Override
    public void run() {
        System.out.println("Initialized " + name);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Initialized");
        latch.countDown();
    }
}
public class Service {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Services disks = new Services("Disks", 2000, latch);
        Services memory = new Services("Memory", 3000, latch);
        Services keyboard = new Services("Keyboard", 1000, latch);

        disks.init();
        memory.init();
        keyboard.init();

        latch.await();

        System.out.println("All systems initialized");
    }
}
