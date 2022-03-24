package ForkJoinPoolEx;

import java.util.concurrent.RecursiveAction;

public class SendRequestAction extends RecursiveAction {

    private final int requestCount;

    SendRequestAction(int requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    protected void compute() {
        if(requestCount < 100) {
            System.out.println("Sending request serially " + requestCount + " in " + Thread.currentThread().getName());
        } else {
            System.out.println("Sending request in parallel " + requestCount);
            SendRequestAction task1 = new SendRequestAction(requestCount / 2);
            SendRequestAction task2 = new SendRequestAction(requestCount / 2);

            task1.fork();
            task2.fork();
        }
    }
}
