import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class State1 {
    int val;
}

class Task1 implements Runnable{

    private final ReadWriteLock lock;
    private final State1 state1;

    Task1(State1 state1, ReadWriteLock lock) {
        this.state1 = state1;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.readLock().lock();
            System.out.println("State value = " + state1.val + " " + Thread.currentThread().getName());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class State {
    public static void main(String[] args) {
        final ReadWriteLock lock = new ReentrantReadWriteLock();
        final State1 state1 = new State1();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; ++i) {
            es.execute(new Task1(state1, lock));
        }
        es.shutdown();
    }
}
