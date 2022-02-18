package ExacutorsSample;

import java.util.concurrent.*;

public class SpaceshipLauncher {

    public void launchAndWaitForReturn(Spaceshipp spaceshipp2) {
        Future<Integer> f = as.submit(spaceshipp2);
        try {
            System.out.println("Waiting for the spaceship to return");
            Integer result = f.get();
            System.out.println("spaceship travelled " + result + " miles");
        } catch(InterruptedException | ExecutionException e) {
            System.out.println("Execution exception caught" + e + " cause " + e.getCause());
            e.printStackTrace();
        }
    }

    private ExecutorService as = Executors.newSingleThreadExecutor();

//    private Executor as = new Executor() {
//        @Override
//        public void execute(Runnable spaceship) {
//            Thread t = new Thread(spaceship);
//            t.start();
//        }
//    };

    public void launch(Spaceshipp spaceshipp){
        as.execute((Runnable) spaceshipp);
   }

   public void turnOff() throws InterruptedException {
       System.out.println("Turning spaceship launcher off");
        as.shutdown();
        as.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
   }
}
