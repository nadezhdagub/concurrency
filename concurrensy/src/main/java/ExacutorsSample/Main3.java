package ExacutorsSample;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;

class Spaceshipp implements Callable<Integer> {

    private final String name;
    private static Random rand = new Random();
    private final boolean isOk;

    Spaceshipp(String name, boolean isOk)  {
        this.name = name;
        this.isOk = isOk;
    }

    @Override
    public Integer call() {
        System.out.println("Spaceship " + name + " launched");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!isOk) {
            throw  new RuntimeException("Spaceship crashed");
        }
        int distance = 1 + rand.nextInt(1000);
        System.out.println("Spaceship " + name + " landed. " + distance + " thousand mils");
        return distance;
    }
}

public class Main3 {
    public static void main(String[] args) throws InterruptedException {
        try {
            Spaceshipp spaceshipp = new Spaceshipp("Apollo", false);
            Spaceshipp spaceshipp2 = new Spaceshipp("Nostromo", true);

            SpaceshipLauncher launcher = new SpaceshipLauncher();
            launcher.launch(spaceshipp);
            launcher.launchAndWaitForReturn(spaceshipp2);

            launcher.turnOff();

            Spaceshipp spaceshipp1 = new Spaceshipp("Soyuz", true);
            launcher.launch(spaceshipp1);
            System.out.println("Main ended");
        } catch (RejectedExecutionException | InterruptedException e) {
            System.out.println("Caught RejectedExecutionException");
            SpaceshipLauncher launcher = new SpaceshipLauncher();
            Spaceshipp spaceshipp3 = new Spaceshipp("Nono", true);
            launcher.launch(spaceshipp3);
            launcher.turnOff();
        }
    }
}
