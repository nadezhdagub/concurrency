package ForkJoinPoolEx;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool fjp = ForkJoinPool.commonPool();

        SendRequestAction task = new SendRequestAction(1000);
        fjp.invoke(task);
    }
}
