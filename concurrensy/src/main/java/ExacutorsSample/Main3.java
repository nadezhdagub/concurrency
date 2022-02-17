package ExacutorsSample;

class Spaceshipp implements Runnable {

    private final String name;

    Spaceshipp(String name)  {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Spaceship " + name + " launched");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Spaceship " + name + " landed");
    }
}

public class Main3 {
    public static void main(String[] args) {
        Spaceship spaceship = new Spaceship("Apollo");
        Spaceship spaceship2 = new Spaceship("Nostromo");

        SpaceshipLauncher launcher = new SpaceshipLauncher();
        launcher.launch(spaceship);
        launcher.launch(spaceship2);
        System.out.println("Main exist");
    }
}
