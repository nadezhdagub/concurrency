import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Statistician implements Callable<Void> {

    private final int id;
    private int count;
    private static Random random = new Random();
    private static ExecutorService es = Executors.newCachedThreadPool();
    private CyclicBarrier cb;

    Statistician(int id) {
        this.id = id;
        count = random.nextInt(1000) + 100;
    }
    
    @Override
    public Void call() throws Exception {
        while(!Thread.interrupted()) {
            log("Statistician " + id + " collecting statistician");
            delay();
            count = count/2;
            log("Statistician " + id + " reported" + id);
            cb.await();
        }
        return null;
    }

    private void delay() throws InterruptedException {
        Thread.sleep(random.nextInt(3000) +1000);
    }

    private void log(String text) {
        System.out.println(text);
    }

    public void collectStatistics(CyclicBarrier cb) {
        this.cb = cb;
        es.submit(this);
    }

    public int getCount() {
        return count;
    }
}

public class Covid {
    public static void main(String[] args) {
        Statistician statistician1 = new Statistician(1);
        Statistician statistician2 = new Statistician(2);
        Statistician statistician3 = new Statistician(3);

        CyclicBarrier cb = new CyclicBarrier(3, () -> {
            int total = statistician1.getCount() + statistician2.getCount() + statistician3.getCount();
            System.out.println("Total: " + total);
        });

        statistician1.collectStatistics(cb);
        statistician2.collectStatistics(cb);
        statistician3.collectStatistics(cb);
    }
}
