package CompleteServicesEx;

import java.util.concurrent.*;

class Feature implements Callable<Integer> {
    
    private final String name;
    private final int days;

    Feature(String name, int days) {
        this.name = name;
        this.days = days;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Features " + name + " work started");
        Thread.sleep(1000 * days);
        System.out.println("Features " + name + " completed");
        return days;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Feature f1 = new Feature("UI", 5);
        Feature f2 = new Feature("Backend", 3);
        Feature f3 = new Feature("Docs", 1);

        ExecutorService es = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> ecs = new ExecutorCompletionService<Integer>(es);

        ecs.submit(f1);
        ecs.submit(f2);
        ecs.submit(f3);

        for (int i = 0; i < 3; ++i) {
            Future<Integer> res =  ecs.take();
            System.out.println(res.get());
        }
    }
}
